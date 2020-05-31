package com.genpact.library.service.impl;

import java.util.Date;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.genpact.library.dao.BookRepository;
import com.genpact.library.dao.LibraryRepository;
import com.genpact.library.entities.Book;
import com.genpact.library.error.ResourceNotFoundException;
import com.genpact.library.service.BookService;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private LibraryRepository libraryRepository;

	@Override
	@Transactional
	public Book saveBook(Long libraryId, Book book) {
		return libraryRepository.findById(libraryId).map(library -> {
			book.setLibrary(library);
			book.setCreatedAt(new Date());
			book.setUpdatedAt(new Date());
			return bookRepository.save(book);
		}).orElseThrow(() -> new ResourceNotFoundException("LibraryId " + libraryId + " not found"));
	}

	@Override
	public Page<Book> getAllBooksByLibraryId(Long libraryId, Pageable pageable) {
		return bookRepository.findByLibraryId(libraryId, pageable);
	}

	@Override
	public Book updateBook(Long libraryId, Long bookId, @Valid Book bookRequest) {
		if (!libraryRepository.existsById(libraryId)) {
			throw new ResourceNotFoundException("LibraryId " + libraryId + " not found");
		}

		return bookRepository.findById(bookId).map(book -> {
			book.setBookName(bookRequest.getBookName());
			book.setAuthorName(bookRequest.getAuthorName());
			book.setContent(bookRequest.getContent());
			book.setUpdatedAt(new Date());
			return bookRepository.save(book);
		}).orElseThrow(() -> new ResourceNotFoundException("BookId " + bookId + "not found"));
	}

	@Override
	public int deleteBook(Long libraryId, Long bookId) {
		return bookRepository.findByIdAndLibraryId(bookId, libraryId).map(book -> {
			bookRepository.delete(book);
			return 0;
		}).orElseThrow(() -> new ResourceNotFoundException(
				"Book not found with id " + bookId + " and libraryId " + libraryId));
	}

}
