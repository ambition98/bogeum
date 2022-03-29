package com.bogeum.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bogeum.web.entity.AccountEntity;
import com.bogeum.web.entity.HashEntity;

public interface HashRepository extends JpaRepository<HashEntity, Long> {
	
}
