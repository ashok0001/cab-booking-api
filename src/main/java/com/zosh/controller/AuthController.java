package com.zosh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zosh.exception.UserException;
import com.zosh.modal.User;
import com.zosh.service.UserService;

@RestController
public class AuthController {

	@Autowired
	private UserService userService;
	
	@PostMapping("/signup")
	public ResponseEntity<User> signupHandler(User user)throws UserException{
		System.out.println("signup ---------- ");
		User createdUser = userService.createUser(user);
		System.out.println("created User " + createdUser);
		return new ResponseEntity<User>(createdUser,HttpStatus.ACCEPTED);
		
	}
}
