package com.learnrestservice.entities;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@NotEmpty(message = "Username is Mandatory field. Please provide username")
	@Column(name = "USER_NAME", length = 50, nullable = false, unique = true)
	private String userName;

	@Size(min = 2, message = "FirstName should be atleast 2 characters")
	@Column(name = "FIRST_NAME", length = 50, nullable = false)
	private String firstName;

	@Column(name = "LAST_NAME", length = 50, nullable = false)
	private String lastName;

	@Column(name = "EMAIL_ADDRESS", length = 50, nullable = false, unique = true)
	private String email;

	@Column(name = "ROLE", length = 50, nullable = false)
	private String role;

	@Column(name = "SSN", length = 50, nullable = false, unique = true)
	private String ssn;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Order> orders = new ArrayList<>();

	public User() {

	}

	public User(String userName, String firstName, String lastName, String email, String role, String ssn) {
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.role = role;
		this.ssn = ssn;
	}

	public User(Long id, String userName, String firstName, String lastName, String email, String role, String ssn) {
		this.id = id;
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.role = role;
		this.ssn = ssn;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public void addOrder(Order order) {
		orders.add(order);
		order.setUser(this);
	}

	public void removeOrder(Order order) {
		order.setUser(null);
		this.orders.remove(order);
	}

	public void removeOrders() {
		Iterator<Order> iterator = this.orders.iterator();
		while (iterator.hasNext()) {
			Order order = iterator.next();
			order.setUser(null);
			iterator.remove();
		}
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", email=" + email + ", role=" + role + ", ssn=" + ssn + "]";
	}

}
