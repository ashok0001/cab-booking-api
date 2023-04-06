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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zosh.config.JwtUtil;
import com.zosh.exception.UserException;
import com.zosh.modal.Driver;
import com.zosh.modal.User;
import com.zosh.repository.DriverRepository;
import com.zosh.repository.UserRepository;
import com.zosh.request.DriversSignupRequest;
import com.zosh.request.LoginRequest;
import com.zosh.request.SignupRequest;
import com.zosh.response.JwtResponce;
import com.zosh.ride.domain.UserRole;
import com.zosh.service.DriverService;
import com.zosh.service.UserService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private DriverRepository driverRepository;
	
	@Autowired
	private DriverService driverService;
	
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@PostMapping("/user/signup")
	public ResponseEntity<JwtResponce> signupHandler(@RequestBody SignupRequest signupRequest)throws UserException, AuthenticationException{
		System.out.println("signup ---------- ");
		
		
		

        // Create new user account
        User user = new User();
        user.setEmail(signupRequest.getEmail());
        user.setPassword(signupRequest.getPassword());
        user.setFullName(signupRequest.getFullName());
        user.setRole(UserRole.USER);
        
        //userDetailsService.save(user);
        Optional<User> emailExist = userRepository.findByEmail(user.getEmail());
        
        JwtResponce jwtResponse=new JwtResponce();
        
		
		if(emailExist.isPresent()) {
			
			jwtResponse.setAuthenticated(false);
	        jwtResponse.setErrorDetails("email already used with another account");
	        jwtResponse.setError(true);
	        
			return new ResponseEntity<JwtResponce>(jwtResponse,HttpStatus.UNAUTHORIZED);
		}
        
        User createdUser=userRepository.save(user);
        

        // Generate JWT token
        String jwt = jwtUtil.generateJwtToken(createdUser);
        
        jwtResponse.setJwt(jwt);
        jwtResponse.setAuthenticated(true);
        jwtResponse.setError(false);
        jwtResponse.setErrorDetails(null);
        jwtResponse.setMessage("Account Created Successfully: "+createdUser.getFullName());
        

        return new ResponseEntity<JwtResponce>(jwtResponse,HttpStatus.ACCEPTED);
    
		
	}
	
	public ResponseEntity<JwtResponce> driverSignupHandler(@RequestBody DriversSignupRequest driverSignupRequest){
		
		Optional<Driver> isEmailExist=driverRepository.findByEmail(driverSignupRequest.getEmail());
		
		JwtResponce jwtResponse=new JwtResponce();
			
		if(isEmailExist.isPresent()) {
			
			jwtResponse.setAuthenticated(false);
		    jwtResponse.setErrorDetails("email already used with another account");
		    jwtResponse.setError(true);
		        
			return new ResponseEntity<JwtResponce>(jwtResponse,HttpStatus.BAD_REQUEST);
		}
		
		Driver createdDriver=driverService.registerDriver(driverSignupRequest);
		
		User user=new User();
		user.setEmail(createdDriver.getEmail());
		
		String jwt = jwtUtil.generateJwtToken(user);
        
        jwtResponse.setJwt(jwt);
        jwtResponse.setAuthenticated(true);
        jwtResponse.setError(false);
        jwtResponse.setErrorDetails(null);
        jwtResponse.setMessage("Account Created Successfully: "+createdDriver.getName());
        
        
		return new ResponseEntity<JwtResponce>(jwtResponse,HttpStatus.ACCEPTED);
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
