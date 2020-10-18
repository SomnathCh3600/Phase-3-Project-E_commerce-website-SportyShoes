package com.sportyshoes.ecommerce.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sportyshoes.ecommerce.dto.PurchaseResponse;
import com.sportyshoes.ecommerce.entities.Purchase;
import com.sportyshoes.ecommerce.exceptions.PurchaseNotFoundException;
import com.sportyshoes.ecommerce.repository.CategoryRepository;
import com.sportyshoes.ecommerce.repository.ProductRepository;
import com.sportyshoes.ecommerce.repository.PurchaseRepository;
import com.sportyshoes.ecommerce.repository.UserRepository;

@RestController
@RequestMapping("/api/v1")
public class PurchaseController {
	
	@Autowired
	private PurchaseRepository purchaserepository;
	@Autowired
	private ProductRepository prodRepo;
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private CategoryRepository catRepo;
	
	@PreAuthorize("hasRole('ADMIN')")
	// Get Purchase by date
	@GetMapping("/purchases/date")
	public List<PurchaseResponse> getPurchaseListByDate(){
		return purchaserepository.getPurchaseListByDate();
	}
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/purchases/date/{date}")
	public List<PurchaseResponse> getPurchaseListDate(@PathVariable String date){
		return purchaserepository.getPurchaseListDate(date);
	}
	@PreAuthorize("hasRole('ADMIN')")
	// Get all purchases
	@GetMapping("/purchases")
	public List<Purchase> getAllPurchases() {
		return this.purchaserepository.findAll();
	}
	@PreAuthorize("hasAnyRole('USER','ADMIN')")
	// Get Purchase by ID
	@GetMapping("/purchases/{id}")
	public Purchase getPurchaseById(@PathVariable String id) {
		return this.purchaserepository.findById(Long.parseLong(id))
				.orElseThrow(() -> new PurchaseNotFoundException("Purchase not found with ID= " + Long.parseLong(id)));
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	// Get Purchase by category
	@GetMapping("/purchases/category")
	public List<PurchaseResponse> getPurchaseListByCategory(){
		return purchaserepository.getPurchaseListByCategory();
	}
	
	// Get Purchase by category
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/purchases/category/{catname}")
	public List<PurchaseResponse> getPurchaseListCategory(@PathVariable String catname) {
		return purchaserepository.getPurchaseListCategory(catname);
	}
	@PreAuthorize("hasAnyRole('USER','ADMIN')")
	// Add a purchase
	@PostMapping("/purchases")
	public Purchase addPurchase(@RequestBody Purchase purchase) {
		return purchaserepository.save(purchase);

	}
	@PreAuthorize("hasAnyRole('USER','ADMIN')")
	// Delete a purchase
	@DeleteMapping("/purchases/{id}")
	public void deletePurchase(@PathVariable String id) {
		// 1. Find a purchase
		Purchase fetchPurchase = this.purchaserepository.findById(Long.parseLong(id))
				.orElseThrow(() -> new PurchaseNotFoundException
						("Purchase not found with ID= " + Long.parseLong(id)));
		// 2. Delete the product
		this.purchaserepository.delete(fetchPurchase);

	}

}
