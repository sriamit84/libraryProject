package com.genpact.library.service.impl;

import java.util.Date;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.genpact.library.dao.LibraryRepository;
import com.genpact.library.entities.Library;
import com.genpact.library.error.ResourceNotFoundException;
import com.genpact.library.service.LibraryService;

@Service
public class LibraryServiceImpl implements LibraryService {

	@Autowired
	private LibraryRepository libraryRepository;

	@Override
	@Transactional
	public Library saveLibrary(Library l) {
		l.setCreatedAt(new Date());
		l.setUpdatedAt(new Date());
		return libraryRepository.save(l);
	}

	@Override
	public Page<Library> findAll(Pageable pageable) {
		return libraryRepository.findAll(pageable);
	}

	@Override
	public Library updateLibrary(Long libraryId, @Valid Library library) {
		return libraryRepository.findById(libraryId).map(libraryObj -> {
			libraryObj.setLibraryName(library.getLibraryName());
			libraryObj.setUpdatedAt(new Date());
			libraryObj.setDescription(library.getDescription());
			return libraryRepository.save(libraryObj);
		}).orElseThrow(() -> new ResourceNotFoundException("LibraryId " + libraryId + " not found"));
	}

	@Override
	public void deleteLibrary(Long libraryId) {
		libraryRepository.findById(libraryId).map(libraryObj -> {
			libraryRepository.delete(libraryObj);
			return "success";
		}).orElseThrow(() -> new ResourceNotFoundException("LibraryId " + libraryId + " not found"));

	}

}
