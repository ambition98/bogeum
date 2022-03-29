package com.bogeum.web.service;

import java.security.NoSuchAlgorithmException;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.bogeum.util.HashingUtil;
import com.bogeum.web.dto.account.AccountDto;
import com.bogeum.web.dto.account.AccountSignupDto;
import com.bogeum.web.entity.HashEntity;
import com.bogeum.web.repository.HashRepository;

@Service
public class HashService {
	
	private final HashRepository hashRepository;
	private final ModelMapper modelMapper;
	
	public HashService(HashRepository hashRepository, ModelMapper modelMapper) {
		this.hashRepository = hashRepository;
		this.modelMapper = modelMapper;
	}

	public AccountDto save(AccountSignupDto dto) throws NoSuchAlgorithmException {
		String salt = HashingUtil.makeNewSalt();
		String digest = HashingUtil.hashing(dto.getEmail(), salt);
		
		HashEntity entity = new HashEntity();
		entity.setEmail(dto.getEmail());
		entity.setSalt(salt);
		entity.setDigest(digest);
		
		entity = hashRepository.save(entity);
		
		AccountDto accountDto = modelMapper.map(entity, AccountDto.class);
		
		return accountDto; 
	}
}
