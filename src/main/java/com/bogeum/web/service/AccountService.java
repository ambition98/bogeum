package com.bogeum.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import com.bogeum.web.entity.AccountEntity;
import com.bogeum.web.repository.AccountRepository;

@Service
public class AccountService {
	
	@Autowired
	private AccountRepository accountRepository;
	
	public List<AccountEntity> getAllAccount() {
		return accountRepository.findAll();
	}
}
