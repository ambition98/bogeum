package com.bogeum.web.restapi;

import java.util.HashMap;
import java.util.Map;

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

import com.bogeum.util.CheckStringValidation;
import com.bogeum.web.dto.AccountDto;
import com.bogeum.web.restapi.model.ApiResponseCode;
import com.bogeum.web.restapi.model.response.CommonResponse;
import com.bogeum.web.service.AccountService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/account")
public class AccountApi {
	
	private final CheckStringValidation checkStringValidation;
	private final AccountService accountService;
	
	public AccountApi(CheckStringValidation checkStringValidation, AccountService accountService) {
		this.checkStringValidation = checkStringValidation;
		this.accountService = accountService;
	}
	
	@GetMapping("/{no}")
	public ResponseEntity<CommonResponse> getAccount(@PathVariable("no") String no) {
		
//		CommonResponse response = new CommonResponse(false, ApiResponseCode.SUCCESS.getCode(), ApiResponseCode.SUCCESS.getMsg());
//		AccountEntity dto = new AccountEntity();
//		dto.set_verified(true);
//		dto.setNo(1);
//		dto.setEmail("ambition65@naver.com");
//		
//		SingleDtoResponse<AccountEntity> response = new SingleDtoResponse<>(true, ApiResponseCode.SUCCESS.getCode(), ApiResponseCode.SUCCESS.getMsg(), dto);
		
		return null;
	}
	
	
	@PostMapping("")
	public ResponseEntity<CommonResponse> postAccount(AccountDto dto) {
		
		
		return null;
	}
	
	@PutMapping("/{no}")
	public ResponseEntity<CommonResponse> putAccount(@PathVariable("no") String no) {
		
		return null;
	}
	
	@DeleteMapping("/{no}")
	public ResponseEntity<CommonResponse> deleteAccount(@PathVariable("no") String no) {
		
		return null;
	}
	
	@GetMapping("/exists")
	public ResponseEntity<CommonResponse> isValidEmail(@RequestParam("email") String email) {
		HttpStatus httpStatus = null;
		ApiResponseCode apiCode = null;
		
		if(accountService.isExistedEmail(email)) {
			httpStatus = HttpStatus.CONFLICT;
			apiCode = ApiResponseCode.DUPLICATED_EMAIL;
		} else {
			httpStatus = HttpStatus.OK;
			apiCode = ApiResponseCode.SUCCESS;
		}
		
		CommonResponse responseBody = new CommonResponse(apiCode);
		
		return new ResponseEntity<>(responseBody, httpStatus);
	}
}
