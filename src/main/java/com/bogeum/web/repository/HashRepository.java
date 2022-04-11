package com.bogeum.web.repository;

import com.bogeum.web.entity.HashEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface HashRepository extends JpaRepository<HashEntity, Long> {
	
	@Query(value = "select he"
				+ " from HashEntity he"
				+ " where he.platformName is null and he.email = ?1")
	Optional<HashEntity> findByEmail(String email);
}
