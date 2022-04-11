package com.bogeum.web.service;

import com.bogeum.exception.ResourceNotFoundException;
import com.bogeum.web.dto.account.AccountDto;
import com.bogeum.web.entity.AccountEntity;
import com.bogeum.web.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {
	
	private final AccountRepository accountRepository;
	private final ModelMapper modelMapper;

	public boolean isExistedEmail(String email) {
		return accountRepository.existsByEmail(email);
	}
	
	public AccountDto findByNo(long no) throws ResourceNotFoundException {
		AccountEntity entity = accountRepository.findByNo(no).orElseThrow(ResourceNotFoundException::new);
		System.out.println(entity);
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
