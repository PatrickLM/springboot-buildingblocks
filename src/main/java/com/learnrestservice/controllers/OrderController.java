package com.learnrestservice.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learnrestservice.entities.Order;
import com.learnrestservice.entities.User;
import com.learnrestservice.exceptions.UserNotFoundException;
import com.learnrestservice.repositories.OrderRepository;
import com.learnrestservice.repositories.UserRepository;

@RestController
@RequestMapping("/users")
public class OrderController {

//	@Autowired
//	private OrderService orderServ;

	@Autowired
	private OrderRepository orderRepo;

	@Autowired
	private UserRepository userRepo;

	@GetMapping("/{userId}/orders")
	public List<Order> getAllOrders(@PathVariable Long userId) throws UserNotFoundException {
		Optional<User> userOptional = userRepo.findById(userId);
		if (!userOptional.isPresent())
			throw new UserNotFoundException("User Was not found");

		return userOptional.get().getOrders();
	}

	@PostMapping("/{userId}/orders")
	public Order createOrder(@PathVariable Long userId, @RequestBody Order order) throws UserNotFoundException {
		
		Optional<User> userOptional = userRepo.findById(userId);
		
		if(!userOptional.isPresent())
			throw new UserNotFoundException("User Not Found");
		
		User user = userOptional.get();
		order.setUser(user);
		return orderRepo.save(order);
	}
	
	@GetMapping("/{userId}/orders/{orderId}")
	public Order getUserOrderByOrderId(@PathVariable Long userId, @PathVariable Long orderId) throws UserNotFoundException {
		
		Optional<Order> orderOptional = orderRepo.getUserOrderById(userId, orderId);
		if(!orderOptional.isPresent())
			throw new UserNotFoundException("There is no user with that order");
		return orderOptional.get();
	}
}
