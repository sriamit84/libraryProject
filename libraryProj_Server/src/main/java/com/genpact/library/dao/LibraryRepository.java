package com.genpact.library.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.genpact.library.entities.Library;

public interface LibraryRepository extends JpaRepository<Library, Long> {

}
