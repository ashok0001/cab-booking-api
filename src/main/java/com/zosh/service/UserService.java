package com.zosh.service;

import org.springframework.stereotype.Service;

import com.zosh.exception.UserException;
import com.zosh.modal.User;


public interface UserService {
	
	public User createUser(User user) throws UserException;
	

	
	public User findUserById(Integer Id) throws UserException;
	
	public User findUserByEmail(String email) throws UserException;
	

}
