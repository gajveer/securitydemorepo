package com.example.securitydemo.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class JDBCSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource datasource;
	

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/login","/register").permitAll().anyRequest()
		.authenticated().and().formLogin().defaultSuccessUrl("/home",true).loginPage("/login")
		.and().logout()
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		.logoutSuccessUrl("/");
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/css/**", "/webjars/**");
	}

	@Override
	/*
	 * By default spring security expect two database table User with the field
	 * username and password and authorities table withe fields username and
	 * authorities
	 */
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().usersByUsernameQuery("select username_username,password,enabled from users where username_username=?")
		.dataSource(this.datasource);
	}

	
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		DelegatingPasswordEncoder encoder = (DelegatingPasswordEncoder) PasswordEncoderFactories
				.createDelegatingPasswordEncoder();
		encoder.setDefaultPasswordEncoderForMatches(NoOpPasswordEncoder.getInstance());
		return encoder;
	}
	 

}
