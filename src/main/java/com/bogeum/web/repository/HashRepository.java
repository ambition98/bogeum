package com.bogeum.web.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bogeum.web.entity.HashEntity;

public interface HashRepository extends JpaRepository<HashEntity, Long> {
	
	@Query(value = "select he"
				+ " from HashEntity he"
				+ " where he.platformName is null and he.email = ?1")
	Optional<HashEntity> findByEmail(String email);
}
