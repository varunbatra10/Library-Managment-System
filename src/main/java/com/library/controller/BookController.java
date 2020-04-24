package com.library.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.Dto.BorrowDto;
import com.library.model.Book;
import com.library.service.BookService;

@RestController
@RequestMapping("/books")
public class BookController {
	
	@Autowired
	BookService bookService;
	
	@PostMapping
	public List<Book> create(@RequestBody List<Book> books) {
		return bookService.save(books);
	}
	
	@DeleteMapping
	public String delete() {
		return bookService.delete();
	}
	
	
	@GetMapping(value = "/find")
	public List<Book> findAll() {
		return bookService.findAll();
	}
	
	@PostMapping(value = "/borrow")
	public String borrow(@RequestBody BorrowDto borrowDto) {
		return bookService.borrow(borrowDto);
	}

	@PostMapping(value = "/return")
	public String returnBook(@RequestBody BorrowDto borrowDto) {
		return bookService.returnBook(borrowDto);
	}
		
	
}
