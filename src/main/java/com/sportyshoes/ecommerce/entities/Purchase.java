package com.sportyshoes.ecommerce.entities;

import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
@Entity
@Table(name="purchase_details")
public class Purchase {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@OneToOne()
	@JoinColumn(name="user_purchase_id")
	private User user;
	
	@OneToMany()
	@JoinColumn(name="product_purchase_id")
	private List<Product> product;

	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
	private String order_date;
	private BigDecimal total;
	public Purchase() {}
	public Purchase(long id, User user, List<Product> product, String order_date, BigDecimal total) {
		super();
		this.id = id;
		this.user = user;
		this.product = product;
		this.order_date = order_date;
		this.total = total;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List<Product> getProduct() {
		return product;
	}
	public void setProduct(List<Product> product) {
		this.product = product;
	}
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
	public String getOrder_date() {
		return order_date;
	}
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
	public void setOrder_date(String order_date) {
		this.order_date = order_date;
	}
	public BigDecimal getTotal() {
		return total;
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	@Override
	public String toString() {
		return "Purchase [id=" + id + ", user=" + user + ", product=" + product + ", order_date=" + order_date
				+ ", total=" + total + "]";
	}
	
	
	
	
	
}
