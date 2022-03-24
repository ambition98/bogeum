package com.bogeum.web.restapi;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;

import org.modelmapper.ModelMapper;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bogeum.exception.ResourceNotFoundException;
import com.bogeum.util.CheckStringValidation;
import com.bogeum.web.dto.account.AccountDto;
import com.bogeum.web.dto.account.AccountSignupDto;
import com.bogeum.web.entity.AccountEntity;
import com.bogeum.web.restapi.model.ApiStatus;
import com.bogeum.web.restapi.model.response.CommonResponse;
import com.bogeum.web.restapi.model.response.ListDtoResponse;
import com.bogeum.web.restapi.model.response.SingleDtoResponse;
import com.bogeum.web.service.AccountService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/account")
public class AccountApi {
	
	private final CheckStringValidation checkStringValidation;
	private final AccountService accountService;
	private final ModelMapper modelMapper;
	
	public AccountApi(CheckStringValidation checkStringValidation, AccountService accountService,
			ModelMapper modelMapper) {
		this.checkStringValidation = checkStringValidation;
		this.accountService = accountService;
		this.modelMapper = modelMapper;
	}

	@GetMapping("")
	public ResponseEntity<CommonResponse> getAllAccounts() {
		List<AccountDto> dtoList = new ArrayList<>();
		List<AccountEntity> entityList = accountService.findAll();
		
		if(entityList.size() == 0)
			return new ResponseEntity<>(new CommonResponse(ApiStatus.RESOURCES_ARE_NOT_FOUND), HttpStatus.NOT_FOUND);
		
		for(AccountEntity entity : entityList) {
			System.out.println(entity);
			AccountDto dto = modelMapper.map(entity, AccountDto.class);
			System.out.println(dto);
			dtoList.add(dto);
		}
		
		ListDtoResponse<AccountDto> response = new ListDtoResponse<>(ApiStatus.SUCCESS, dtoList);
		
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/{no}")
	public ResponseEntity<CommonResponse> getAccount(@PathVariable String no) {
		Long longNo;
		AccountEntity entity = null;
		
		try {
			longNo = Long.parseLong(no);
			entity = accountService.findByNo(longNo);
			
		} catch (NumberFormatException e) {
			return new ResponseEntity<>(new CommonResponse(ApiStatus.NOT_A_NUMBER), HttpStatus.BAD_REQUEST);
			
		} catch (ResourceNotFoundException e) {
			return new ResponseEntity<>(new CommonResponse(ApiStatus.RESOURCES_ARE_NOT_FOUND), HttpStatus.NOT_FOUND);
		}
		
		AccountDto dto = modelMapper.map(entity, AccountDto.class);
		SingleDtoResponse<AccountDto> response = new SingleDtoResponse<>(ApiStatus.SUCCESS, dto);
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@PostMapping("")
	public ResponseEntity<CommonResponse> postAccount(AccountSignupDto signupDto) {
		System.out.println(signupDto);
		AccountEntity entity = new AccountEntity();
		entity.setEmail(signupDto.getEmail());
		
		System.out.println(entity);
		entity = accountService.save(entity);
		System.out.println(entity);
		
		return new ResponseEntity<>(new CommonResponse(ApiStatus.SUCCESS), HttpStatus.OK);
	}
	
	@PutMapping("/{no}")
	public ResponseEntity<CommonResponse> putAccount(@PathVariable String no) {
		
		return null;
	}
	
	@DeleteMapping("/{no}")
	public ResponseEntity<CommonResponse> deleteAccount(@PathVariable String no) {
		
		return null;
	}
	
	@GetMapping("/exists")
	public ResponseEntity<CommonResponse> isValidEmail(@RequestParam("email") String email) {
		if(accountService.isExistedEmail(email)) {
			return new ResponseEntity<>(
					new CommonResponse(ApiStatus.DUPLICATED_EMAIL)
					, HttpStatus.CONFLICT);
			
		} else {
			return new ResponseEntity<>(new CommonResponse(ApiStatus.SUCCESS), HttpStatus.OK);
		}
	}
}
