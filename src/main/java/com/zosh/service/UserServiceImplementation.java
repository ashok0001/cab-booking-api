package com.zosh.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zosh.exception.UserException;
import com.zosh.modal.User;
import com.zosh.repository.UserRepository;
import com.zosh.request.SignupRequest;

@Service
public class UserServiceImplementation implements UserService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public User createUser(User user) throws UserException {
		
		User emailExist = findUserByEmail(user.getEmail());
		
		if(emailExist!=null)throw new UserException("Email Already Used With Another Account");
		

		
		return userRepository.save(user);
		
	}

	

	@Override
	public User findUserById(Integer userId) throws UserException {
		
		Optional<User> opt=userRepository.findById(userId);
		
		if(opt.isPresent()) {
			return opt.get();
		}
		throw new UserException("user not found with id "+userId);
	}

	@Override
	public User findUserByEmail(String email) throws UserException {
		
		Optional<User> opt=userRepository.findByEmail(email);
		
		if(opt.isPresent()) {
			return opt.get();
		}
		throw new UserException("user not found with email "+email);
	}

}
