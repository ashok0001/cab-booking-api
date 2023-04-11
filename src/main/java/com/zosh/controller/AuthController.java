package com.zosh.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.zosh.config.JwtUtil;
import com.zosh.exception.UserException;
import com.zosh.modal.Driver;
import com.zosh.modal.User;
import com.zosh.repository.DriverRepository;
import com.zosh.repository.LicenseRepository;
import com.zosh.repository.UserRepository;
import com.zosh.repository.VehicleRepository;
import com.zosh.request.DriversSignupRequest;
import com.zosh.request.LoginRequest;
import com.zosh.request.SignupRequest;
import com.zosh.response.JwtResponce;
import com.zosh.ride.domain.UserRole;
import com.zosh.service.CustomUserDetailsService;
import com.zosh.service.DriverService;
import com.zosh.service.UserService;

import jakarta.validation.Valid;

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
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private VehicleRepository vehicleRepository;
	
	@Autowired
	private LicenseRepository licenseRepository;
	
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@PostMapping("/user/signup")
	public ResponseEntity<JwtResponce> signupHandler(@Valid @RequestBody SignupRequest signupRequest)throws UserException, AuthenticationException{
		

        
        User user = userRepository.findByEmail(signupRequest.getEmail());
        
        JwtResponce jwtResponse=new JwtResponce();
        
		
		if(user!=null) {
			throw new UserException("User Already Exist With Email "+signupRequest.getEmail());
		}
        
		String encodedPassword = passwordEncoder.encode(signupRequest.getPassword());

        // Create new user account
        User createdUser = new User();
        createdUser.setEmail(signupRequest.getEmail());
        createdUser.setPassword(encodedPassword);
        createdUser.setFullName(signupRequest.getFullName());
        createdUser.setRole(UserRole.USER);
        
        User savedUser=userRepository.save(createdUser);
        
        Authentication authentication=new UsernamePasswordAuthenticationToken(savedUser.getEmail(), savedUser.getPassword());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        
        // Generate JWT token
        String jwt = jwtUtil.generateJwtToken(authentication);
        
        jwtResponse.setJwt(jwt);
        jwtResponse.setAuthenticated(true);
        jwtResponse.setError(false);
        jwtResponse.setErrorDetails(null);
        jwtResponse.setType(UserRole.USER);
        jwtResponse.setMessage("Account Created Successfully: "+savedUser.getFullName());
        

        return new ResponseEntity<JwtResponce>(jwtResponse,HttpStatus.ACCEPTED);
    
		
	}
	
	@PostMapping("/driver/signup")
	public ResponseEntity<JwtResponce> driverSignupHandler(@RequestBody DriversSignupRequest driverSignupRequest){
		
		Driver driver = driverRepository.findByEmail(driverSignupRequest.getEmail());
		
		JwtResponce jwtResponse=new JwtResponce();
			
		if(driver==null) {
			
			jwtResponse.setAuthenticated(false);
		    jwtResponse.setErrorDetails("email already used with another account");
		    jwtResponse.setError(true);
		        
			return new ResponseEntity<JwtResponce>(jwtResponse,HttpStatus.BAD_REQUEST);
		}
		
		licenseRepository.save(driverSignupRequest.getLicense());
		vehicleRepository.save(driverSignupRequest.getVehicle());
		
		Driver createdDriver=driverService.registerDriver(driverSignupRequest);
		
		Authentication authentication = new UsernamePasswordAuthenticationToken(createdDriver.getEmail(), createdDriver.getPassword());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        
		String jwt = jwtUtil.generateJwtToken(authentication);
        
        jwtResponse.setJwt(jwt);
        jwtResponse.setAuthenticated(true);
        jwtResponse.setError(false);
        jwtResponse.setErrorDetails(null);
        jwtResponse.setType(UserRole.DRIVER);
        jwtResponse.setMessage("Account Created Successfully: "+createdDriver.getName());
        
        
		return new ResponseEntity<JwtResponce>(jwtResponse,HttpStatus.ACCEPTED);
	}
	
	
	@PostMapping("/signin")
    public ResponseEntity<JwtResponce> signin(@RequestBody LoginRequest loginRequest) {
        String username = loginRequest.getEmail();
        String password = loginRequest.getPassword();
        
        System.out.println(username +" ----- "+password);
        
        Authentication authentication = authenticate(username, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        
        String jwt = jwtUtil.generateJwtToken(authentication);
        
        JwtResponce jwtResponse=new JwtResponce();
        
        jwtResponse.setJwt(jwt);
        jwtResponse.setAuthenticated(true);
        jwtResponse.setError(false);
        jwtResponse.setErrorDetails(null);
        jwtResponse.setMessage("Account Created Successfully: ");

        return new ResponseEntity<JwtResponce>(jwtResponse,HttpStatus.OK);
    }
	
	private Authentication authenticate(String username, String password) {
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
        
        System.out.println("sign in userDetails - "+userDetails);
        
        if (userDetails == null) {
        	System.out.println("sign in userDetails - null " + userDetails);
            throw new BadCredentialsException("Invalid username or password");
        }
        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
        	System.out.println("sign in userDetails - password not match " + userDetails);
            throw new BadCredentialsException("Invalid username or password");
        }
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }

	
//	@GetMapping("/user/signin")
//	public ResponseEntity<JwtResponce> driverSigninHandler(HttpServletRequest request) throws UserException{
//		
//        String username = request.getHeader("username");
//        String password = request.getHeader("password");
//        
//        // Validate username and password
////        User user = userService.getUserByUsername(username);
//        
//        User user = userRepository.findByEmail(username);
//        
//        
//        if(user==null) {
//        	throw new UserException("user not found with email "+username);
//        	
//        }
//        
//        if (!passwordEncoder.matches(password, user.getPassword())) {
//        	throw new BadCredentialsException("Invalid username or password");
//        }
//        
//        String jwt = jwtUtil.generateJwtToken(user);
//        	
//        JwtResponce jwtResponse=new JwtResponce();
//        jwtResponse.setError(false);
//        jwtResponse.setAuthenticated(true);
//        jwtResponse.setJwt(jwt);
//        
//    
//        return new ResponseEntity<JwtResponce>(jwtResponse,HttpStatus.ACCEPTED);
//		
//		
//	}
}
