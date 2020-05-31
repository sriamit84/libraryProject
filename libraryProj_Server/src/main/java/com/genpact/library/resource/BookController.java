package com.genpact.library.resource;

import com.genpact.library.entities.Book;
import com.genpact.library.error.ResourceNotFoundException;
import com.genpact.library.service.BookService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author Amit Srivastava
 *
 */
@RestController
public class BookController {

	@Autowired
	private BookService bookService;

	
	/**
	 * This rest API is to get the list of all the libraries
	 * @param libraryId
	 * @param pageable
	 * @return Page Response with Libary details
	 */
	@GetMapping("/libraries/{libraryId}/books")
	public Page<Book> getAllBooksByLibraryId(@PathVariable(value = "libraryId") Long libraryId, Pageable pageable) {
		return bookService.getAllBooksByLibraryId(libraryId, pageable);
	}

	/**
	 * This rest API is to add book to a library
	 * @param libraryId
	 * @param book
	 * @return {@link Book} 
	 */
	@PostMapping("/libraries/{libraryId}/books")
	public Book addBook(@PathVariable(value = "libraryId") Long libraryId, @Valid @RequestBody Book book) {
		return bookService.saveBook(libraryId, book);
	}

	/**
	 * This rest is to update the book details
	 * @param libraryId
	 * @param bookId
	 * @param book
	 * @return {@link Book}
	 */
	@PutMapping("/libraries/{libraryId}/books/{bookId}")
	public Book updateBook(@PathVariable(value = "libraryId") Long libraryId,
			@PathVariable(value = "bookId") Long bookId, @Valid @RequestBody Book book) {
		return bookService.updateBook(libraryId, bookId, book);
	}

	/**
	 * This rest API is to delete the book
	 * @param libraryId
	 * @param bookId
	 * @return {@linkplain ResponseEntity}
	 */
	@DeleteMapping("/libraries/{libraryId}/books/{bookId}")
	public ResponseEntity<?> deleteBook(@PathVariable(value = "libraryId") Long libraryId,
			@PathVariable(value = "bookId") Long bookId) {
		int status = bookService.deleteBook(libraryId, bookId);
		if (status == 0) {
			return ResponseEntity.ok().build();
		} else {
			throw new ResourceNotFoundException("Book not found with " + bookId);
		}
	}
}