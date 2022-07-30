package com.lti.dto;

import com.lti.entity.Address;
import com.lti.entity.LandDetails;
import com.lti.entity.User;

public class RegisterDto {

	private LandDetails landdetails;
	private User user;
	private Address address;

	public LandDetails getLanddetails() {
		return landdetails;
	}

	public void setLanddetails(LandDetails landdetails) {
		this.landdetails = landdetails;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

}
