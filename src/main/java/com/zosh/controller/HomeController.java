package com.zosh.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zosh.response.MessageResponse;

@RestController
public class HomeController {
	
	@GetMapping({"/api","/"})
	public ResponseEntity<MessageResponse> homeController(){
		MessageResponse res=new MessageResponse("welcome to cab booking api");
		
		return new ResponseEntity<MessageResponse>(res,HttpStatus.ACCEPTED);
		
	}

}
