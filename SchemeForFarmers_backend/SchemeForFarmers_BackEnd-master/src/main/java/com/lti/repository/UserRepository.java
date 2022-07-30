package com.lti.repository;

import com.lti.entity.Address;
import com.lti.entity.LandDetails;
import com.lti.entity.User;

public interface UserRepository {

	void save(User u);

	int findByEmailAndPass(String email, String pass);

	User findbyId(int id);

	boolean isUserAvailable(String email);

	void saveLandDetails(LandDetails landDetails);

	int getUserId(String email);

	void saveAddress(Address address);

	String getUserRole(int id);

	String getUserName(int id);
	

}
