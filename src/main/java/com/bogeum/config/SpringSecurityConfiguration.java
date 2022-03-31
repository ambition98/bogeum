package com.bogeum.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		
		http.cors().disable();
		
		http.headers().frameOptions().disable();
		
		http.authorizeHttpRequests()
				.anyRequest().permitAll();
		
		
	}
	
	@Bean
	public PasswordEncoder bCryptEncoder() {
		return new BCryptPasswordEncoder();
	}
}
