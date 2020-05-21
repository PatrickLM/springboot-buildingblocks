package com.learnrestservice.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.learnrestservice.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

	@Query("SELECT o FROM Order o WHERE order_id =:orderId AND user_id=:userId")
	Optional<Order> getUserOrderById(Long userId, Long orderId);

}
