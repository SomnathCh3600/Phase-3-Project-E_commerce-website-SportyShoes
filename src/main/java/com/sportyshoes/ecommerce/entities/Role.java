package com.sportyshoes.ecommerce.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Role")
public class Role {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private String role;
	public Role() {}
	public Role(long id, String role) {
		super();
		this.id = id;
		this.role = role;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	@Override
	public String toString() {
		return "Role [id=" + id + ", role=" + role + "]";
	}
	
}
