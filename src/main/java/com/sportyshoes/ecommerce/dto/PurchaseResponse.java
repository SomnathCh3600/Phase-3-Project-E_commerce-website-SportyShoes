package com.sportyshoes.ecommerce.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class PurchaseResponse {
	
	private String name;
	private BigDecimal price;
	private String fname;
	private String lname;
	private String order_date;
	private BigDecimal total;
	private String cat_name;
	public PurchaseResponse() {}
	public PurchaseResponse(String name, BigDecimal price, String fname, String lname, String order_date,
			BigDecimal total) {
		super();
		this.name = name;
		this.price = price;
		this.fname = fname;
		this.lname = lname;
		this.order_date = order_date;
		this.total = total;
	}
	
	
	public PurchaseResponse(String name, BigDecimal price, String fname, String lname, BigDecimal total,
			String cat_name) {
		super();
		this.name = name;
		this.price = price;
		this.fname = fname;
		this.lname = lname;
		this.total = total;
		this.cat_name = cat_name;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
	public String getOrder_date() {
		return order_date;
	}
	public void setOrder_date(String order_date) {
		this.order_date = order_date;
	}
	public BigDecimal getTotal() {
		return total;
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	
	
	public String getCategory() {
		return cat_name;
	}
	public void setCategory(String cat_name) {
		this.cat_name = cat_name;
	}
	@Override
	public String toString() {
		return "PurchaseResponse [name=" + name + ", price=" + price + ", fname=" + fname
				+ ", lname=" + lname + ", order_date=" + order_date + ", total=" + total + ", cat_name=" + cat_name + "]";
	}
	
	

}
