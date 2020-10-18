package com.sportyshoes.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sportyshoes.ecommerce.dto.ProductResponse;
import com.sportyshoes.ecommerce.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	
	@Query("SELECT new com.sportyshoes.ecommerce.dto.ProductResponse(r.name, r.price, s.cat_name) FROM Product r JOIN r.category s ")
	public List<ProductResponse> getProductList();

}
