package com.library.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "books")
public class Book {

		@Id
		@Column(name = "book_id", nullable = false)
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private int id;
		
		@Column(name = "name")
		private String name;
		
		@Column(name = "total_qty")
		private int totalQty;
		
		@Column(name = "borrowed_qty")
		private int borrowedQty;


		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getTotalQty() {
			return totalQty;
		}

		public void setTotalQty(int totalQty) {
			this.totalQty = totalQty;
		}

		public int getBorrowedQty() {
			return borrowedQty;
		}

		public void setBorrowedQty(int borrowedQty) {
			this.borrowedQty = borrowedQty;
		}
		
}
