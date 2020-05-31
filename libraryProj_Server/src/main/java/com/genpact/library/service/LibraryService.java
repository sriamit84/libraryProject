package com.genpact.library.service;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.genpact.library.entities.Library;

public interface LibraryService {
	
	public Library saveLibrary(Library l);
	
	public Page<Library> findAll(Pageable pageable);

	public Library updateLibrary(Long libraryId,  @Valid Library library);

	public void deleteLibrary(Long libraryId);

}
