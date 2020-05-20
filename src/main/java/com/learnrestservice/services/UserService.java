package com.learnrestservice.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learnrestservice.entities.User;
import com.learnrestservice.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepo;

	public List<User> getAllUsers() {
		return userRepo.findAll();
	}

	public Optional<User> getUserById(Long id) {
		Optional<User> user = userRepo.findById(id);
		return user;
	}

	public User createUser(User user) {
		return userRepo.save(user);
	}
	
	public User updateUserById(Long id, User user) {
		user.setId(id);
		return userRepo.save(user);
	}
	
	public void deleteUserById(Long id) {
		if(userRepo.findById(id).isPresent()) {
			userRepo.deleteById(id);
		}
	}
	
	public User getUserByUsername(String username) {
		return userRepo.findByUserName(username);
	}
}
