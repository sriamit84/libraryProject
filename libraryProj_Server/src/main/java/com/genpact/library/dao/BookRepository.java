package com.genpact.library.dao;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.genpact.library.entities.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

	Page<Book> findByLibraryId(Long libraryId, Pageable pageable);

	Optional<Book> findByIdAndLibraryId(Long bookId, Long libraryId);

}
