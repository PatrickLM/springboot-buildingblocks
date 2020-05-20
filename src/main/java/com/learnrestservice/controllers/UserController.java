package com.learnrestservice.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learnrestservice.entities.User;
import com.learnrestservice.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userSer;

	@GetMapping
	public List<User> getAllUsers() {
		return userSer.getAllUsers();
	}

	@GetMapping("/{id}")
	public Optional<User> getUserById(@PathVariable("id") Long id) {

		return userSer.getUserById(id);
	}

	@GetMapping("/byusername/{username}")
	public User getUserByUsername(@PathVariable("username") String username) {
		return userSer.getUserByUsername(username);
	}

	@PostMapping
	public User createUser(@RequestBody User user) {
		return userSer.createUser(user);
	}

	@PutMapping("/{id}")
	public User updateUserById(@PathVariable("id") Long id, @RequestBody User user) {
		return userSer.updateUserById(id, user);
	}

	@DeleteMapping("/{id}")
	public void deleteUserById(@PathVariable("id") Long id) {
		userSer.deleteUserById(id);
	}
}
