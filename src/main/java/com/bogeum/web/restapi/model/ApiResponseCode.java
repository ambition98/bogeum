package com.bogeum.web.restapi.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonFormat(shape = Shape.OBJECT)
public enum ApiResponseCode {
	
	/*    일반 응답 코드    */
	SUCCESS(1, "Succeed"),
	
	
	/*      오류 코드     */
	// -99 ~ -1: 공통 오류
	UNKNOWN(-1, "[PATAL] UNKNOWN ERROR"),
	INVALID_STRING_PATTERN(-2, "Invalid string pattern"),
	
	// -199 ~ -100: User account 관련 오류 
	DUPLICATED_EMAIL(-100, "Already used email");
	
	private int code;
	private String msg;
}
