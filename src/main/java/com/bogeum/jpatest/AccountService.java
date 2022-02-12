package com.bogeum.jpatest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

@Service
public class AccountService {
	
	@Autowired
	private AccountRepository accountRepository;
	
	public List<AccountEntity> getAllAccount() {
		return accountRepository.findAll();
	}
}
