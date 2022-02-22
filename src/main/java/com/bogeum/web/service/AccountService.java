package com.bogeum.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bogeum.web.entity.AccountEntity;
import com.bogeum.web.repository.AccountRepository;

@Service
public class AccountService {
	
	@Autowired
	private AccountRepository accountRepository;
	
	public AccountEntity findByEmail(String email) {
		return accountRepository.findByEmail(email);
	}
}
