package com.sportyshoes.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sportyshoes.ecommerce.dto.PurchaseResponse;
import com.sportyshoes.ecommerce.entities.Purchase;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
	
	@Query("Select new com.sportyshoes.ecommerce.dto.PurchaseResponse"
			+ "(p.name, p.price, u.fname, "
			+ "u.lname, o.order_date, "
			+ "o.total)"
			+ "From Purchase o Join o.user u Join o.product p")
	public List<PurchaseResponse> getPurchaseListByDate();
	
	@Query(value="Select new com.sportyshoes.ecommerce.dto.PurchaseResponse"
			+ "(p.name, p.price, u.fname, u.lname, o.order_date, o.total)"
			+ "From Purchase o Join o.user u Join o.product p WHERE o.order_date LIKE %?1%")
	public List<PurchaseResponse> getPurchaseListDate(String date);
	
	
	@Query("Select new com.sportyshoes.ecommerce.dto.PurchaseResponse"
			+ "(p.name, p.price, u.fname, u.lname, o.total, c.cat_name)"
			+ "From Purchase o Join o.user u Join o.product p Join p.category c ")
	public List<PurchaseResponse> getPurchaseListByCategory();
	
	@Query("Select new com.sportyshoes.ecommerce.dto.PurchaseResponse"
			+ "(p.name, p.price, u.fname, u.lname, o.total, c.cat_name)"
			+ "From Purchase o Join o.user u Join o.product p Join p.category c WHERE c.cat_name LIKE %?1%")
	public List<PurchaseResponse> getPurchaseListCategory(String catname);

	

}
