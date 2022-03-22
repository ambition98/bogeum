package com.bogeum.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bogeum.web.entity.AccountEntity;
import com.bogeum.web.repository.AccountRepository;

@Service
public class AccountService {
	
	@Autowired
	private AccountRepository accountRepository;
	
	public boolean isExistedEmail(String email) {
		return accountRepository.existsByEmail(email);
	}
	
	public AccountEntity findByNo(long no) {
		return accountRepository.getById(no);
	}
	
	public List<AccountEntity> findAll() {
		return accountRepository.findAll();
	}
	
	public AccountEntity save(AccountEntity entity) {
		return accountRepository.save(entity);
	}
}
