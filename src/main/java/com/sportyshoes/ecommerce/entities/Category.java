package com.sportyshoes.ecommerce.entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="category")
public class Category {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="cat_id")
	private long id;
	private String cat_name;
	
	public Category(){}

	public Category(long id, String cat_name) {
		super();
		this.id = id;
		this.cat_name = cat_name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return cat_name;
	}

	public void setName(String cat_name) {
		this.cat_name = cat_name;
	}


	@Override
	public String toString() {
		return "Category [id=" + id + ", cat_name=" + cat_name + "]";
	}
	
	
		
}
