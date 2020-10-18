package com.sportyshoes.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sportyshoes.ecommerce.entities.Role;



public interface RoleRepository extends JpaRepository<Role, Long> {

}
