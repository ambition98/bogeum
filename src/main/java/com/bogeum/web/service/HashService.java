package com.bogeum.web.service;

import com.bogeum.exception.ResourceNotFoundException;
import com.bogeum.web.dto.account.AccountDto;
import com.bogeum.web.dto.account.AccountSignDto;
import com.bogeum.web.entity.HashEntity;
import com.bogeum.web.repository.AccountRepository;
import com.bogeum.web.repository.HashRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class HashService {

	private final HashRepository hashRepository;
	private final AccountRepository accountRepository;
	private final ModelMapper modelMapper;
	private final PasswordEncoder passwdEncoder;

	public String getDigestByEmail(String email) throws ResourceNotFoundException {
		boolean isExists = accountRepository.existsByEmail(email);
		HashEntity entity = null;

		if(isExists) {
			entity = hashRepository.findByEmail(email).orElseThrow(ResourceNotFoundException::new);
		} else {
			throw new ResourceNotFoundException();
		}

		return entity.getDigest();
	}

	public AccountDto save(AccountSignDto dto) {
		String digest = passwdEncoder.encode(dto.getPasswd());

		HashEntity entity = new HashEntity();
		entity.setEmail(dto.getEmail());
		entity.setDigest(digest);

		entity = hashRepository.save(entity);
		AccountDto accountDto = modelMapper.map(entity, AccountDto.class);
		log.info("accountDto: {}", accountDto);

		return accountDto;
	}
}

