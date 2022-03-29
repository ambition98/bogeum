package com.bogeum.web.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.bogeum.exception.ResourceNotFoundException;
import com.bogeum.web.dto.account.AccountDto;
import com.bogeum.web.entity.AccountEntity;
import com.bogeum.web.repository.AccountRepository;

@Service
public class AccountService {
	
	private final AccountRepository accountRepository;
	private final ModelMapper modelMapper;
	
	public AccountService(AccountRepository accountRepository, ModelMapper modelMapper) {
		this.accountRepository = accountRepository;
		this.modelMapper = modelMapper;
	}

	public boolean isExistedEmail(String email) {
		return accountRepository.existsByEmail(email);
	}
	
	public AccountDto findByNo(long no) throws ResourceNotFoundException {
		AccountEntity entity = accountRepository.findById(no).orElseThrow(ResourceNotFoundException::new);
		
		return modelMapper.map(entity, AccountDto.class);
	}
	
	public List<AccountDto> findAll() throws ResourceNotFoundException {
		List<AccountDto> dtoList = new ArrayList<>();
		List<AccountEntity> entityList = accountRepository.findAll();
		
		if(entityList.size() == 0)
			throw new ResourceNotFoundException();
	
		for(AccountEntity entity : entityList) {
			System.out.println(entity);
			AccountDto dto = modelMapper.map(entity, AccountDto.class);
			System.out.println(dto);
			dtoList.add(dto);
		}
		
		return dtoList;
	}
}
