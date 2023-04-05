package com.zosh.controller;

import java.util.Optional;

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
import com.zosh.repository.UserRepository;
import com.zosh.request.LoginRequest;
import com.zosh.request.SignupRequest;
import com.zosh.response.JwtResponce;
import com.zosh.service.UserService;

@RestController
public class AuthController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepository userRepository;
	
//	@Autowired
//	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@PostMapping("/signup")
	public ResponseEntity<JwtResponce> signupHandler(@RequestBody SignupRequest signupRequest)throws UserException, AuthenticationException{
		System.out.println("signup ---------- ");
		
		
		

        // Create new user account
        User user = new User();
        user.setEmail(signupRequest.getEmail());
        user.setPassword(signupRequest.getPassword());
        user.setFullName(signupRequest.getFullName());
        
        //userDetailsService.save(user);
        Optional<User> emailExist = userRepository.findByEmail(user.getEmail());
        
        
		
		if(emailExist.isPresent()) {
			return new ResponseEntity<JwtResponce>(new JwtResponce("email already used with another account", false),HttpStatus.UNAUTHORIZED);
		}
        
        User createdUser=userRepository.save(user);
        
//        Authentication authentication = authenticationManager.authenticate( new UsernamePasswordAuthenticationToken(signupRequest.getEmail(), signupRequest.getPassword()));
//		
//        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Generate JWT token
        String jwt = jwtUtil.generateJwtToken(createdUser);

        return new ResponseEntity<JwtResponce>(new JwtResponce(jwt, true),HttpStatus.ACCEPTED);
    
		
	}
	
//	@PostMapping("/signin")
//	public ResponseEntity<JwtResponce> signinHandler(@RequestBody LoginRequest loginReques){
//		
//		
//		
//		try {
//			Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginReques.getEmail(), loginReques.getPassword()));
//			
//			SecurityContextHolder.getContext().setAuthentication(authentication);
//			
//			String jwt=jwtUtil.generateJwtToken(authentication);
//			
//			return new ResponseEntity<JwtResponce>(new JwtResponce(jwt,true),HttpStatus.ACCEPTED);
//			
//		} catch (Exception e) {
//	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new JwtResponce("Invalid username or password",false));
//
//		}
//		
//	}
}
