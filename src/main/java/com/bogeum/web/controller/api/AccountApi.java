package com.bogeum.web.controller.api;

import com.bogeum.exception.ResourceNotFoundException;
import com.bogeum.util.CheckStringValidation;
import com.bogeum.web.controller.api.model.ApiStatus;
import com.bogeum.web.controller.api.model.response.CommonResponse;
import com.bogeum.web.controller.api.model.response.ListDtoResponse;
import com.bogeum.web.controller.api.model.response.SingleDtoResponse;
import com.bogeum.web.dto.account.AccountDto;
import com.bogeum.web.dto.account.AccountSignDto;
import com.bogeum.web.service.AccountService;
import com.bogeum.web.service.HashService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
		long requestId = Thread.currentThread().getId();
		long longNo;
		AccountDto dto;
		log.info("[{}] Request, GET account/api/{}", requestId, no);
		try {
			longNo = Long.parseLong(no);
			dto = accountService.findByNo(longNo);
			
		} catch (NumberFormatException e) {// 숫자가 아닌 문자로 요청
			log.info("[{}] Error, '{}' is not number", requestId, no);
			return new ResponseEntity<>(new CommonResponse(ApiStatus.NOT_A_NUMBER), HttpStatus.BAD_REQUEST);
			
		} catch (ResourceNotFoundException e) { // 찾은 결과가 존재하지 않음
			log.info("[{}] Error, Not found about '{}' resource", requestId, no);
			return new ResponseEntity<>(new CommonResponse(ApiStatus.RESOURCES_ARE_NOT_FOUND), HttpStatus.NOT_FOUND);
		}
		
		log.info("[{}] Success, GET account/api/{}", requestId, longNo);
		log.info("[{}] responseDto: {}", requestId, dto.toString());
		SingleDtoResponse<AccountDto> response = new SingleDtoResponse<>(ApiStatus.SUCCESS, dto);
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@PostMapping("")
	public ResponseEntity<CommonResponse> postAccount(AccountSignDto signupDto) {
		long requestId = Thread.currentThread().getId();
		log.info("[{}] Request, POST /api/account ,email: '{}'" , requestId, signupDto.getEmail());

		// 이메일 정규식 확인
		if(!CheckStringValidation.checkEmail(signupDto.getEmail())) {
			log.info("[{}] Error, '{}' is invalid email pattern", requestId, signupDto.getEmail());
			return new ResponseEntity<>(new CommonResponse(ApiStatus.INVALID_EMAIL_PATTERN), HttpStatus.BAD_REQUEST);
		}

		// 이메일 중복 확인 (서버단에서 한번 더)
		if(accountService.isExistedEmail(signupDto.getEmail())) {
			log.info("[{}] Error, Email '{}' is already exist", requestId, signupDto.getEmail());
			return new ResponseEntity<>(new CommonResponse(ApiStatus.DUPLICATED_EMAIL), HttpStatus.CONFLICT);
		}
		
		AccountDto dto = hashService.save(signupDto);
		
		log.info("[{}] Success, POST /api/account", requestId);
		log.info("[{}] responseDto: {}", requestId, dto.toString());
		SingleDtoResponse<AccountDto> response = new SingleDtoResponse<>(ApiStatus.SUCCESS, dto);
		
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
		long requestId = Thread.currentThread().getId();
		
		log.info("[{}] Requested, GET /api/account/exists ,email: '{}'", requestId, email);
		
		if(accountService.isExistedEmail(email)) {
			log.info("[{}] Error, Email '{}' is already exist", requestId, email);
			return new ResponseEntity<>(new CommonResponse(ApiStatus.DUPLICATED_EMAIL), HttpStatus.CONFLICT);
		} else {
			log.info("[{}] Success, GET /api/account/exists ,email: '{}'", requestId, email);
			return new ResponseEntity<>(new CommonResponse(ApiStatus.SUCCESS), HttpStatus.OK);
		}
	}
}
