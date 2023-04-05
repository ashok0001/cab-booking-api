package com.zosh.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class AppConfig {
	
	public SecurityFilterChain securityConfigration(HttpSecurity http) throws Exception {
		
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
		.authorizeHttpRequests().requestMatchers(HttpMethod.POST,"/signup").permitAll()
		.requestMatchers(HttpMethod.GET,"/").permitAll().anyRequest().authenticated()
		.and().csrf().disable().httpBasic().and().formLogin();
		
		return http.build();
		
	}

}
