package com.zosh.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobleException {
	
	@ExceptionHandler(UserException.class)
	public ResponseEntity<ErrorDetails> userExceptionHandler(UserException ue, WebRequest req){
		
		ErrorDetails err=new ErrorDetails(ue.getMessage(),req.getDescription(false),LocalDateTime.now());
		
		
		return new ResponseEntity<ErrorDetails>(err,HttpStatus.ACCEPTED);
		
	}
	@ExceptionHandler(DriverException.class)
	public ResponseEntity<ErrorDetails> driverrExceptionHandler(DriverException ue, WebRequest req){
		
		ErrorDetails err=new ErrorDetails(ue.getMessage(),req.getDescription(false),LocalDateTime.now());
		
		
		return new ResponseEntity<ErrorDetails>(err,HttpStatus.ACCEPTED);
		
	}
	
	
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorDetails> otherExceptionHandler(UserException ue, WebRequest req){
		
		ErrorDetails err=new ErrorDetails(ue.getMessage(),req.getDescription(false), LocalDateTime.now());
		
		
		return new ResponseEntity<ErrorDetails>(err,HttpStatus.ACCEPTED);
		
	}

}
