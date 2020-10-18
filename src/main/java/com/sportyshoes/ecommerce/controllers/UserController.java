package com.sportyshoes.ecommerce.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sportyshoes.ecommerce.entities.Role;
import com.sportyshoes.ecommerce.entities.User;
import com.sportyshoes.ecommerce.exceptions.UserNotFoundException;
import com.sportyshoes.ecommerce.repository.RoleRepository;
import com.sportyshoes.ecommerce.repository.UserRepository;

@RestController
@RequestMapping("/api/v1")
public class UserController {

	@Autowired
	private UserRepository userrepository;
	
	private RoleRepository roleRepository;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	@PreAuthorize("hasRole('ADMIN')")
	// Get all users
	@GetMapping("/users")
	public List<User> getAllUsers() {
		return this.userrepository.findAll();
	}
	@PreAuthorize("hasRole('ADMIN')")
	// Get User by ID
	@GetMapping("/users/{id}")
	public User getUserById(@PathVariable String id) {
		return this.userrepository.findById(Long.parseLong(id))
				.orElseThrow(() -> new UserNotFoundException("User not found with ID= " + Long.parseLong(id)));
	}

	// Get User by Name
	@GetMapping("/user/{name}")
	public List<User> getByName(@PathVariable String name) {
		if (name != null) {
			return userrepository.getByName(name);
		} else {
			return this.userrepository.findAll();
		}
	}

	// Add a user
	@PostMapping("/user")
	public User addUser(@RequestBody User user) {
		String pwd = user.getPassword();
		String encodedPass = passwordEncoder.encode(pwd);
		user.setPassword(encodedPass);
		return this.userrepository.save(user);

	}

	// Update a user
	@PutMapping("/user/{id}")
	public User updateUser(@RequestBody User user, Role role, @PathVariable String id) {
		// 1. Find a user
		User fetchUser = this.userrepository.findById(Long.parseLong(id))
				.orElseThrow(() -> new UserNotFoundException("User not found with ID= " + Long.parseLong(id)));
		// 2. Set new values
		fetchUser.setFname(user.getFname());
		fetchUser.setLname(user.getLname());
		fetchUser.setAge(user.getAge());
		fetchUser.setAddress(user.getAddress());
		fetchUser.setEmail(user.getEmail());
		fetchUser.setUsername(user.getUsername());
		String pwd = user.getPassword();
		String encodedPass = passwordEncoder.encode(pwd);
		fetchUser.setPassword(encodedPass);
		fetchUser.setRoles(user.getRoles());
		// 3. Save a user
		return this.userrepository.save(fetchUser);
		

	}

	// Delete a user
	@PreAuthorize("hasAuthority('ADMIN')")
	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable String id) {
		// 1. Find a user
		User fetchUser = this.userrepository.findById(Long.parseLong(id))
				.orElseThrow(() -> new UserNotFoundException("User not found with ID= " + Long.parseLong(id)));
		// 2. Delete the product
		this.userrepository.delete(fetchUser);

	}

}
