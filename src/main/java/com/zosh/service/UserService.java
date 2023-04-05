package com.zosh.service;

import org.springframework.stereotype.Service;

import com.zosh.exception.UserException;
import com.zosh.modal.User;

@Service
public interface UserService {
	
	public User createUser(User user) throws UserException;
	
	public User findUserByUsername(User user) throws UserException;
	
	public User findUserById(User user) throws UserException;
	
	public User findUserByEmail(User user) throws UserException;
	

}
