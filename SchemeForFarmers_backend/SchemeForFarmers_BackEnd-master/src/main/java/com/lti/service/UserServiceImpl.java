package com.lti.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.lti.dto.RegisterDto;
import com.lti.entity.Address;
import com.lti.entity.LandDetails;
import com.lti.entity.User;
import com.lti.exception.UserServiceException;
import com.lti.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepo;

	@Override
	public boolean register(RegisterDto regdto) {

		try {
			if (userRepo.isUserAvailable(regdto.getUser().getEmail())) {
				throw new UserServiceException("User Already registered");
			}

			userRepo.save(regdto.getUser());

			int id = userRepo.getUserId(regdto.getUser().getEmail());
			User usr = userRepo.findbyId(id);

			Address address = regdto.getAddress();
			address.setUser(usr);
			userRepo.saveAddress(regdto.getAddress());

			if (regdto.getUser().getRole().equals("Farmer") && regdto.getLanddetails() != null) {

				LandDetails landdetails = regdto.getLanddetails();
				landdetails.setUser(usr);
				userRepo.saveLandDetails(landdetails);
				return true;

			} else if (regdto.getUser().getRole().equals("Bidder") && regdto.getLanddetails() == null) {
				return true;
			} else {
				return false;
			}

		} catch (EmptyResultDataAccessException e) {
			throw new UserServiceException("User Register Failed");
		}

	}

	@Override
	public User login(String email, String password) {
		try {
			if (!userRepo.isUserAvailable(email)) {
				throw new UserServiceException("User not registered");
			}
			int id = userRepo.findByEmailAndPass(email, password);
			User user = userRepo.findbyId(id);
			return user;
		} catch (EmptyResultDataAccessException e) {
			throw new UserServiceException("Incorrect Email/Password");
		}
	}

}
