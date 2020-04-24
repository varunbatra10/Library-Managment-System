package com.library.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "borrow")
public class Borrow {

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Id;
	
	@Column(name = "book_id")
	private int bookId;
	
	@Column(name = "borrowed_by")
	private int borrowedBy;

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public int getBorrowedBy() {
		return borrowedBy;
	}

	public void setBorrowedBy(int borrowedBy) {
		this.borrowedBy = borrowedBy;
	}

//	@OneToOne
//	@JoinColumn(name = "book_id")
//	private Book book;
	


}
