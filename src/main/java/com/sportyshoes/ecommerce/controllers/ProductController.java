package com.sportyshoes.ecommerce.controllers;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sportyshoes.ecommerce.dto.ProductResponse;
import com.sportyshoes.ecommerce.entities.Product;
import com.sportyshoes.ecommerce.exceptions.ProductNotFoundException;
import com.sportyshoes.ecommerce.repository.CategoryRepository;
import com.sportyshoes.ecommerce.repository.ProductRepository;

@RestController
public class ProductController {
	
	//Dependency Injection
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;

	// Get all products
	@GetMapping("/")
	public List<ProductResponse> getProductList(){
		return productRepository.getProductList();
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	//Get all products
	@GetMapping("/products")
	public List<Product> getAllProducts(){
		return this.productRepository.findAll();
	}
	@PreAuthorize("hasRole('ADMIN')")
	// Get Product by ID
	@GetMapping("/products/{id}")
	public Product getAProduct(@PathVariable String id) {
		return this.productRepository.findById(Long.parseLong(id))
				.orElseThrow(() -> new ProductNotFoundException
						("Product not found with ID= " + Long.parseLong(id)));
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	// Add a product
	@PostMapping("/products")
	public Product addProduct(@RequestBody Product product) {
		return productRepository.save(product);
		 
	}
	@PreAuthorize("hasRole('ADMIN')")
	// Update a product
	@PutMapping("/products/{id}")
	public Product updateProduct(@RequestBody Product product, @PathVariable String id) {
		//1. Find a product
		Product fetchProduct = this.productRepository.findById(Long.parseLong(id))
				.orElseThrow(()-> new ProductNotFoundException
						("Product not found with ID= "+Long.parseLong(id)));
		//2. Set new values
		fetchProduct.setId(product.getId());
		fetchProduct.setName(product.getName());
		fetchProduct.setPrice(product.getPrice());
		fetchProduct.setDateAdded(product.getDateAdded());
		fetchProduct.setCategory(product.getCategory());
		
		//3. Save a product
		return this.productRepository.save(fetchProduct);

	}
	@PreAuthorize("hasRole('ADMIN')")
	// Delete a product
	@DeleteMapping("/products/{id}")
	public void deleteProduct(@PathVariable String id) {
		// 1. Find a product
		Product fetchProduct = this.productRepository.findById(Long.parseLong(id))
				.orElseThrow(() -> new ProductNotFoundException
						("Product not found with ID= " + Long.parseLong(id)));
		// 2. Delete the product
		this.productRepository.delete(fetchProduct);

	}
	
}
