package com.library.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {

	@Id
	@Column(name = "user_id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Id;

	@Column(name = "name")
	private String name;

	@Column(name = "borrowed_qty")
	private int borrowedQty;

	public int getId() {
		return Id;
	}

	public int getBorrowedQty() {
		return borrowedQty;
	}

	public void setBorrowedQty(int borrowedQty) {
		this.borrowedQty = borrowedQty;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
