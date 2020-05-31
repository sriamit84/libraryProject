package com.genpact.library.resource;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.genpact.library.entities.Library;
import com.genpact.library.error.ResourceNotFoundException;
import com.genpact.library.service.LibraryService;

@RestController
public class LibraryController {

	@Autowired
	private LibraryService libraryService;

	/**
	 * This rest API is to get all the libraries
	 * @param pageable
	 * @return Pageable Response of Libraries
	 */
	@GetMapping("/libraries")
	public Page<Library> getAllLibraries(Pageable pageable) {
		return libraryService.findAll(pageable);
	}

	/**
	 * This rest API is to create a Library
	 * @param library
	 * @return {@link Library}
	 */
	@PostMapping("/libraries")
	public Library createLibrary(@Valid @RequestBody Library library) {
		return libraryService.saveLibrary(library);
	}

	/**
	 * This rest API is to update the Library Details
	 * @param libraryId
	 * @param library
	 * @return {@link Library}
	 */
	@PutMapping("/libraries/{libraryId}")
	public Library updateLibrary(@PathVariable Long libraryId, @Valid @RequestBody Library library) {
		return libraryService.updateLibrary(libraryId, library);
	}

	/**
	 * This rest API is to delete the Library
	 * @param libraryId
	 * @return {@link ResponseEntity}}
	 */
	@DeleteMapping("/libraries/{libraryId}")
	public ResponseEntity<?> deleteLibrary(@PathVariable Long libraryId) {
		try {
			libraryService.deleteLibrary(libraryId);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			throw new ResourceNotFoundException(e.getMessage());
		}
	}

}
