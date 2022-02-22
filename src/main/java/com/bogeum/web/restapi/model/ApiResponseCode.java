package com.bogeum.web.restapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ApiResponseCode {
	
	/*    일반 응답 코드    */
	SUCCESS(1, "succeed"),
	
	
	/*      오류 코드     */
	// -99 ~ -1: 공통 오류
	UNKNOWN(-1, "[PATAL] UNKNOWN ERROR"),
	INVALID_STRING_PATTERN(-2, "Invalid String Pattern"),
	
	// -199 ~ -100: User account 관련 오류 
	DUPLICATED_EMAIL(-1, "Duplicated user email");
	
	private int code;
	private String msg;
}
