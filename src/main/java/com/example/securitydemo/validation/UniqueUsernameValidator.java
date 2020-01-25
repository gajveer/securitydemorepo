package com.example.securitydemo.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.securitydemo.model.UserDto;
import com.example.securitydemo.repository.AuthoritiesRepository;


public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, Object> {

	@Autowired
	private AuthoritiesRepository repository;

	@Override
	public boolean isValid(Object user, ConstraintValidatorContext context) {
		
		return (repository.findByUsername(((UserDto)user).getUsername()) == null) ;
		
	}

}
