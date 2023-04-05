package com.zosh.service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.zosh.repository.UserRepository;

public class UsersUserDetailService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<com.zosh.modal.User> opt= userRepository.findByEmail(username);
		
		if(opt.isPresent()) {
			com.zosh.modal.User user=opt.get();
			List<GrantedAuthority> auths=new ArrayList<>();
			return new User(user.getEmail(), user.getPassword(), auths);
		}
		
		
		throw new BadCredentialsException("Bad Credential");
	}

}
