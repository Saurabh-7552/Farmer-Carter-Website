package com.lti.service;

import com.lti.dto.RegisterDto;
import com.lti.entity.User;

public interface UserService {

	public boolean register(RegisterDto regdto);

	User login(String email, String password);

}