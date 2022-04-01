package com.bogeum.web.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bogeum.web.entity.AccountEntity;

public interface AccountRepository extends JpaRepository<AccountEntity, Long> {

	Optional<AccountEntity> findByNo(long no);
	
	List<AccountEntity> findAll();
	
	@Query(value = "select case when count(*) > 0 then true else false end"
				+ " from AccountEntity an"
				+ " where an.platformName is null and an.email = ?1")
	boolean existsByEmail(String email);
	
}
