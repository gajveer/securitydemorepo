package com.example.securitydemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.securitydemo.entity.Authorities;
import com.example.securitydemo.entity.Users;

@Repository
public interface AuthoritiesRepository extends JpaRepository<Authorities, String>{	
	
	Authorities findByUsername(String userName);
}
