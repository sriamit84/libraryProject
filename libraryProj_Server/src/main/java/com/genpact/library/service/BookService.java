package com.genpact.library.service;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.genpact.library.entities.Book;

public interface BookService {
	
	public Book saveBook(Long libraryId, Book book);
	
	public Page<Book> getAllBooksByLibraryId(Long libraryId, Pageable pageable);

	public Book updateBook(Long libraryId, Long bookId, @Valid Book book);

	public int deleteBook(Long libraryId, Long bookId);

}
