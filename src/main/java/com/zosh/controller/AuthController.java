package com.zosh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.zosh.config.JwtUtil;
import com.zosh.exception.UserException;
import com.zosh.modal.User;
import com.zosh.request.SignupRequest;
import com.zosh.service.UserService;

@RestController
public class AuthController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	private JwtUtil jwtUtil;
	
	@PostMapping("/signup")
	public ResponseEntity<String> signupHandler(@RequestBody SignupRequest signupRequest)throws UserException, AuthenticationException{
		System.out.println("signup ---------- ");
		
		
		

        // Create new user account
        User user = new User();
        user.setEmail(signupRequest.getEmail());
        user.setPassword(signupRequest.getPassword());
        user.setFullName(signupRequest.getFullName());
        
        //userDetailsService.save(user);
        User createdUser = userService.createUser(user);
        
        
        Authentication authentication = authenticationManager.authenticate( new UsernamePasswordAuthenticationToken(signupRequest.getEmail(), signupRequest.getPassword()));
		
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Generate JWT token
        String token = jwtUtil.generateJwtToken(authentication);

        return new ResponseEntity<String>(token,HttpStatus.ACCEPTED);
    
		
	}
}
