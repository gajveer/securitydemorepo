package com.example.securitydemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.securitydemo.entity.Authorities;
import com.example.securitydemo.entity.Users;
import com.example.securitydemo.model.UserDto;
import com.example.securitydemo.repository.AuthoritiesRepository;
import com.example.securitydemo.repository.UserRepository;

@Service("userRegistrationService")
public class UserRegistrationServiceImpl implements UserRegistrationService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private AuthoritiesRepository authoritiesRepository;
	
	@Override
	public void createUser(UserDto user) {
		
		
		Authorities authority= new Authorities();
		authority.setUsername(user.getUsername());
		authority.setAuthority("USER");
		Users users = new Users();		
		users.setUsername(authority);
		users.setFirstName(user.getFirstname()); 
		users.setLastName(user.getLastname());
		users.setEmail(user.getEmail()); 
		users.setPassword(encoder.encode(user.getPassword()));
		authoritiesRepository.save(authority);
		userRepository.save(users);
		
	}

}
