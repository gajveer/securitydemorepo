package com.example.securitydemo.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import com.example.securitydemo.validation.PasswordConfirmed;
import com.example.securitydemo.validation.PasswordPolicy;
import com.example.securitydemo.validation.UniqueEmail;
import com.example.securitydemo.validation.UniqueUsername;

@PasswordConfirmed
@UniqueUsername
public class UserDto {
	
	@NotEmpty(message="Please enter your firstname")
	private String firstname;
	@NotEmpty(message="Please enter your lastname")
	private String lastname;
	@NotEmpty(message="Please enter a username")
	private String username;
	@UniqueEmail
	@NotEmpty(message="Please enter an email")
	@Email(message="Email is not valid")
	private String email;
	@PasswordPolicy	
	@NotEmpty(message="Please enter in a password")
	private String password;
	@NotEmpty(message="Please confirm your password")
	private String confirmPassword;
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
		
	
}
