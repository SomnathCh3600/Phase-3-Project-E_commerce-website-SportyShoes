package com.sportyshoes.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sportyshoes.ecommerce.entities.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
