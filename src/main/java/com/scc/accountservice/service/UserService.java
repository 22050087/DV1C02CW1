package com.scc.accountservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import com.scc.accountservice.model.User;
import com.scc.accountservice.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	public User createUser(User user) {
		return userRepository.save(user);
	}
		
	public void saveUser(User user) {
		userRepository.save(user);
	}
	
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}
	
	public User getUserById(Long id) {
		User user = userRepository.findById(id).get();

		if (user == null) {
			throw new RuntimeException("User not found");
		}
		return user;
	}

	public User updateUser(Long id, User userDetails) {
		User user = userRepository.findById(id).get();
		user.setFirstName(userDetails.getFirstName());
		user.setLastName(userDetails.getLastName());
		user.setEmail(userDetails.getEmail());
		user.setPassword(userDetails.getPassword());
	        
	    return userRepository.save(user);                                
	}

	public void deleteUserById(Long id) {
		User user = userRepository.findById(id).get();


		if (user == null) {
			throw new RuntimeException("User not found");
		}
		userRepository.deleteById(id);
	}
}
