package com.bogeum.web.restapi;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
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
import com.bogeum.util.HashingUtil;
import com.bogeum.web.dto.account.AccountDto;
import com.bogeum.web.dto.account.AccountSignupDto;
import com.bogeum.web.entity.AccountEntity;
import com.bogeum.web.entity.HashEntity;
import com.bogeum.web.restapi.model.ApiStatus;
import com.bogeum.web.restapi.model.response.CommonResponse;
import com.bogeum.web.restapi.model.response.ListDtoResponse;
import com.bogeum.web.restapi.model.response.SingleDtoResponse;
import com.bogeum.web.service.AccountService;
import com.bogeum.web.service.HashService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/account")
public class AccountApi {
	
	private final AccountService accountService;
	private final HashService hashService; 
	
	public AccountApi(AccountService accountService, HashService hashService) {
		this.accountService = accountService;
		this.hashService = hashService;
	}

	@GetMapping("")
	public ResponseEntity<CommonResponse> getAllAccounts() {
		List<AccountDto> dtoList;
		try {
			dtoList = accountService.findAll();
		} catch (ResourceNotFoundException e) {
			return new ResponseEntity<>(new CommonResponse(ApiStatus.RESOURCES_ARE_NOT_FOUND), HttpStatus.NOT_FOUND);
		}
		
		ListDtoResponse<AccountDto> response = new ListDtoResponse<>(ApiStatus.SUCCESS, dtoList);
		
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/{no}")
	public ResponseEntity<CommonResponse> getAccount(@PathVariable String no) {
		Long longNo;
		AccountDto dto = null;
		
		try {
			longNo = Long.parseLong(no);
			dto = accountService.findByNo(longNo);
			
		} catch (NumberFormatException e) {// 숫자가 아닌 문자로 요청
			return new ResponseEntity<>(new CommonResponse(ApiStatus.NOT_A_NUMBER), HttpStatus.BAD_REQUEST);
			
		} catch (ResourceNotFoundException e) { // 찾은 결과가 존재하지 않음
			return new ResponseEntity<>(new CommonResponse(ApiStatus.RESOURCES_ARE_NOT_FOUND), HttpStatus.NOT_FOUND);
		}
		
		SingleDtoResponse<AccountDto> response = new SingleDtoResponse<>(ApiStatus.SUCCESS, dto);
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@PostMapping("")
	public ResponseEntity<CommonResponse> postAccount(AccountSignupDto signupDto) {

		// 이메일 정규식 확인
		if(!CheckStringValidation.checkEmail(signupDto.getEmail()))
			return new ResponseEntity<>(new CommonResponse(ApiStatus.INVALID_EMAIL_PATTERN), HttpStatus.BAD_REQUEST);
		
		// 이메일 중복 확인 (서버단에서 한번 더)
		if(accountService.isExistedEmail(signupDto.getEmail()))
			return new ResponseEntity<>(new CommonResponse(ApiStatus.DUPLICATED_EMAIL), HttpStatus.CONFLICT);
		
		AccountDto dto = null;
		try {
			dto = hashService.save(signupDto);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return new ResponseEntity<>(new CommonResponse(ApiStatus.API_SERVER_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		SingleDtoResponse<AccountDto> response = new SingleDtoResponse<AccountDto>(ApiStatus.SUCCESS, dto);
		
		return new ResponseEntity<>(response, HttpStatus.OK);
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
