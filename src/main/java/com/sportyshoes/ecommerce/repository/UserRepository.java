package com.sportyshoes.ecommerce.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sportyshoes.ecommerce.entities.User;

public interface UserRepository extends JpaRepository<User, Long>  {
	
	User findByusername(String username);
	
	@Query("Select u From User u WHERE u.fname LIKE %?1%")
	public List<User> getByName(String name);
}
