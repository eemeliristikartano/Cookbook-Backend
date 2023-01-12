package com.cookbook.cookbookdatabase.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class NewUserValidation {
	
	@NotBlank
	@Size(min = 5, max = 20)
	private String username = "";
	
	@NotBlank
	@Size(min = 7, max = 32)
	private String password = "";
	
	@NotBlank
	@Size(min = 7, max = 32)
	private String passwordCheck = "";
	
	@NotBlank
	private String role = "USER";

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordCheck() {
		return passwordCheck;
	}

	public void setPasswordCheck(String passwordCheck) {
		this.passwordCheck = passwordCheck;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "NewUserValidation [username=" + username + ", password=" + password + ", passwordCheck=" + passwordCheck
				+ ", role=" + role + "]";
	}
	
	

}
