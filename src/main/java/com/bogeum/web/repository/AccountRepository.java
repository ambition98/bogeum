package com.bogeum.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bogeum.web.entity.AccountEntity;

public interface AccountRepository extends JpaRepository<AccountEntity, Long> {
	
	public AccountEntity findByEmail(String email);
}
