package com.bogeum.web.restapi;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bogeum.exception.ResourceNotFoundException;
import com.bogeum.web.dto.account.AccountSignDto;
import com.bogeum.web.restapi.model.ApiStatus;
import com.bogeum.web.restapi.model.response.CommonResponse;
import com.bogeum.web.service.HashService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/account")
@RequiredArgsConstructor
public class AccountAuthApi {
	
	private final PasswordEncoder passwordEncoder;
	private final HashService hashService;
	
	@GetMapping("/auth")
	public ResponseEntity<CommonResponse> auth(AccountSignDto dto) {
		long requestId = Thread.currentThread().getId();
		String digest;
		log.debug("[{}] Requested, /api/account/auth, email: '{}'", requestId, dto.getEmail());
		
		
		try {
			digest = hashService.getDigestByEmail(dto.getEmail());
		} catch (ResourceNotFoundException e) { //일치하는 이메일을 찾을 수 없음
			log.info("[{}] Error, Not exist email, email: '{}'", requestId, dto.getEmail());
			return new ResponseEntity<>(new CommonResponse(ApiStatus.INVALID_EMAIL_OR_PASSWORD), HttpStatus.UNAUTHORIZED);
		} 
		
		boolean isCorrectPw = passwordEncoder.matches(dto.getPasswd(), digest);
		if(isCorrectPw) { //성공
			log.debug("[{}] Success, /api/account/auth, email: '{}'", requestId, dto.getEmail());
			return new ResponseEntity<>(new CommonResponse(ApiStatus.SUCCESS), HttpStatus.OK);
			
		} else { // 패스워드가 틀림
			log.info("[{}] Error, Invalid password, email: '{}'", requestId, dto.getEmail());
			return new ResponseEntity<>(new CommonResponse(ApiStatus.INVALID_EMAIL_OR_PASSWORD), HttpStatus.UNAUTHORIZED);
		}
	}
}