package com.bogeum.web.restapi;

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

import com.bogeum.util.CheckStringValidation;
import com.bogeum.web.dto.account.AccountDto;
import com.bogeum.web.dto.account.AccountSignupDto;
import com.bogeum.web.entity.AccountEntity;
import com.bogeum.web.restapi.model.ApiResponseCode;
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
		List<AccountEntity> entityList = accountService.findAll();
		List<AccountDto> dtoList = new ArrayList<>();
		
		for(AccountEntity entity : entityList) {
			System.out.println(entity);
			AccountDto dto = modelMapper.map(entity, AccountDto.class);
			System.out.println(dto);
			dtoList.add(dto);
		}
		
		ListDtoResponse<AccountDto> response = new ListDtoResponse<>(ApiResponseCode.SUCCESS, dtoList);
		
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/{no}")
	public ResponseEntity<CommonResponse> getAccount(@PathVariable String no) {
		Long longNo;
		
		try {
			longNo = Long.parseLong(no);
		} catch (NumberFormatException e) {
			return new ResponseEntity<>(new CommonResponse(ApiResponseCode.NOT_A_NUMBER), HttpStatus.BAD_REQUEST);
		}
		
		AccountEntity entity = accountService.findByNo(longNo);
		
		if(entity == null) {
			return new ResponseEntity<>(new CommonResponse(ApiResponseCode.RESOURCES_ARE_NOT_FOUNT), HttpStatus.NOT_FOUND);
		}
		
		AccountDto dto = modelMapper.map(entity, AccountDto.class);
		SingleDtoResponse<AccountDto> response = new SingleDtoResponse<>(ApiResponseCode.SUCCESS, dto);
		
		return ResponseEntity.ok().body(response);
	
	}
	
	@PostMapping("")
	public ResponseEntity<CommonResponse> postAccount(AccountSignupDto signupDto) {
		System.out.println("test");
		System.out.println(signupDto);
		
		return null;
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
