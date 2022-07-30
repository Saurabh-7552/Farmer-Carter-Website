package com.lti.status;

public class LoginStatus extends Status {
	private int UserId;
	private String name;
	private String Role;

	public int getUserId() {
		return UserId;
	}

	public String getRole() {
		return Role;
	}

	public void setRole(String role) {
		Role = role;
	}

	public void setUserId(int UserId) {
		this.UserId = UserId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}