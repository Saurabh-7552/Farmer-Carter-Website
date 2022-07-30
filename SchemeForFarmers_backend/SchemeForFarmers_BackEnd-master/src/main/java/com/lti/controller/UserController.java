package com.lti.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lti.dto.LoginDto;
import com.lti.dto.RegisterDto;
import com.lti.entity.User;
import com.lti.exception.UserServiceException;
import com.lti.service.UserService;
import com.lti.status.LoginStatus;
import com.lti.status.Status;
import com.lti.status.Status.StatusType;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping(path = "/register")
	public Status register(@RequestBody RegisterDto regdto) {
		try {
			if (userService.register(regdto)) {
				Status status = new Status();
				status.setStatus(com.lti.status.Status.StatusType.SUCCESS);
				status.setMessage("Registration Successful");
				return status;
			} else {
				throw new UserServiceException("Registration Unsucessful");
			}
		} catch (UserServiceException e) {
			Status status = new Status();
			status.setStatus(com.lti.status.Status.StatusType.FAILURE);
			status.setMessage(e.getMessage());
			return status;
		}
	}

	@PostMapping("/login")
	public LoginStatus login(@RequestBody LoginDto logindto) {

		try {
			System.out.println(logindto.getEmail());
			User user = userService.login(logindto.getEmail(), logindto.getPassword());

			// request.getSession().setAttribute("Userid", user.getId());

			LoginStatus loginstatus = new LoginStatus();
			loginstatus.setStatus(com.lti.status.Status.StatusType.SUCCESS);
			loginstatus.setMessage("Login Sucessful");
			loginstatus.setUserId(user.getId());
			loginstatus.setName(user.getFullname());
			loginstatus.setRole(user.getRole());
			return loginstatus;

		} catch (UserServiceException e) {

			LoginStatus loginstatus = new LoginStatus();
			loginstatus.setStatus(StatusType.FAILURE);
			loginstatus.setMessage(e.getMessage());
			return loginstatus;
		}
	}
}
