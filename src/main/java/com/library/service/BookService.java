package com.library.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.library.Dto.BorrowDto;
import com.library.model.Book;
import com.library.model.Borrow;
import com.library.model.User;
import com.library.repository.BookRepository;
import com.library.repository.BorrowRepository;
import com.library.repository.UserRepository;

@Transactional
@Service(value = "bookService")
public class BookService {

	@Autowired
	BookRepository bookRepository;

	@Autowired
	BorrowRepository borrowRepository;

	@Autowired
	UserRepository userRepository;

	public List<Book> save(List<Book> books) {
		for (Book book : books) {
			bookRepository.save(book);
		}
		return books;
	}

	public String delete() {
		List<Book> books = bookRepository.findAll();
		for (Book book : books) {
			bookRepository.delete(book);
		}
		return "Books deleted";
	}

	public List<Book> findAll() {
		return bookRepository.findByAvailability();
	}

	public String borrow(BorrowDto borrowDto) {
		int bookId = borrowDto.getBookId();
		int userId = borrowDto.getUserId();

		User user = userRepository.findById(userId).get();
		if (user != null) {
			if (user.getBorrowedQty() < 2) {
				Borrow borrow = borrowRepository.findByBookIdAndUserId(bookId, userId);
				if (borrow == null) {
					borrow = new Borrow();
					Book book = bookRepository.findById(bookId).get();
					if (book.getBorrowedQty() < book.getTotalQty()) {
						book.setBorrowedQty(book.getBorrowedQty() + 1);
						borrow.setBookId(bookId);
						borrow.setBorrowedBy(userId);
						user.setBorrowedQty(user.getBorrowedQty() + 1);

						userRepository.save(user);
						borrowRepository.save(borrow);
						bookRepository.save(book);
						return "Book borrowed";
					} else {
						return "No copies of the book are available";
					}
				} else {
					return "You can't borrow more than one copy";
				}
			} else {
				return "You can't borrow more than 2 books";
			}
		} else {
			return "User doesn't exist";
		}

	}

	public String returnBook(BorrowDto borrowDto) {
		int userId = borrowDto.getUserId();
		int bookId = borrowDto.getBookId();
		if (bookId == 0) {
			List<Borrow> booksBorrowed = borrowRepository.findByUserId(userId);
			if (!booksBorrowed.isEmpty()) {
				for (Borrow borrow : booksBorrowed) {
					borrowRepository.delete(borrow);
					Book book = bookRepository.findById(borrow.getBookId()).get();
					book.setBorrowedQty(book.getBorrowedQty() - 1);
					bookRepository.save(book);
				}
				return "All Books are returned";
			} else {
				return "Books can't be returned";
			}
		} else {
			Book book = bookRepository.findById(bookId).get();
			Borrow borrow = borrowRepository.findByBookIdAndUserId(bookId, userId);
			if (borrow != null) {
				borrowRepository.delete(borrow);
				book.setBorrowedQty(book.getBorrowedQty() - 1);
				bookRepository.save(book);
				return "Book is returned";
			} else {
				return "Book can't be returned";
			}
		}
	}

}
