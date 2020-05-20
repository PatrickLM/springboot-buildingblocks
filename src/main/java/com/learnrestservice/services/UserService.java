package com.learnrestservice.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.learnrestservice.entities.User;
import com.learnrestservice.exceptions.UserExistsException;
import com.learnrestservice.exceptions.UserNotFoundException;
import com.learnrestservice.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepo;

	public List<User> getAllUsers() {
		return userRepo.findAll();
	}

	public Optional<User> getUserById(Long id) throws UserNotFoundException {
		Optional<User> user = userRepo.findById(id);
		if (!user.isPresent()) {
			throw new UserNotFoundException("User not found in user Repository");
		}
		return user;
	}

	public User createUser(User user) throws UserExistsException {
		User existingUser = userRepo.findByUserName(user.getUserName());
		
		if(existingUser != null) {
			throw new UserExistsException("User already exists in repository");
		}
		return userRepo.save(user);
	}

	public User updateUserById(Long id, User user) throws UserNotFoundException {
		if (user.getId() != id) {
			throw new UserNotFoundException("Bad input");
		}

		Optional<User> optionalUser = userRepo.findById(id);
		if (!optionalUser.isPresent()) {
			throw new UserNotFoundException("User not found in user Repository");
		}

		user.setId(id);
		return userRepo.save(user);
	}

	public void deleteUserById(Long id) {

		Optional<User> optionalUser = userRepo.findById(id);
		if (!optionalUser.isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found in user Repository");
		}

		userRepo.deleteById(id);
	}

	public User getUserByUsername(String username) {
		return userRepo.findByUserName(username);
	}
}
