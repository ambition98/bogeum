package com.bogeum.web.restapi;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bogeum.util.CheckStringValidation;
import com.bogeum.web.service.AccountService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/account")
public class AccountRestApi {
	
	private final CheckStringValidation checkStringValidation;
	private final AccountService accountService;
	
	public AccountRestApi(CheckStringValidation checkStringValidation, AccountService accountService) {
		this.checkStringValidation = checkStringValidation;
		this.accountService = accountService;
	}

	@PostMapping("/{email}")
	public Map<String, Object> checkEmail(@PathVariable("email") String email) {
		Map<String, Object> map = new HashMap<>();
		boolean isValid = true;
		String errorMsg = "";
		
		if(!checkStringValidation.checkValidEmailPattern(email)) {
			log.info("Invalid email pattern");
			errorMsg = "이메일 형식이 잘못되었습니다.";
			isValid = false;
		}
		
		if(accountService.findByEmail(email) == null) {
			log.info("Duplicated email");
			errorMsg = "이미 사용중인 이메일입니다.";
			isValid = false;
		}
		
		map.put("isValid", isValid);
		map.put("errorMsg", errorMsg);
		
		return map;
	}
}
