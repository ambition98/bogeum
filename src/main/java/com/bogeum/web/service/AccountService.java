package com.bogeum.web.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.bogeum.exception.ResourceNotFoundException;
import com.bogeum.web.dto.account.AccountDto;
import com.bogeum.web.entity.AccountEntity;
import com.bogeum.web.repository.AccountRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountService {
	
	private final AccountRepository accountRepository;
	private final ModelMapper modelMapper;

	/**
	 * ACCOUNT 테이블의 PLATFORM_NAME 이 NULL 인 경우만 가져온다.
	 * 즉, 소셜로그인으로 저장된 email은 가져오지 않는다.
	 * 
	 * @param email
	 * @return digest
	 * @throws ResourceNotFoundException
	 */
	public boolean isExistedEmail(String email) {
		return accountRepository.existsByEmail(email);
	}
	
	public AccountDto findByNo(long no) throws ResourceNotFoundException {
		AccountEntity entity = accountRepository.findByNo(no).orElseThrow(ResourceNotFoundException::new);
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
