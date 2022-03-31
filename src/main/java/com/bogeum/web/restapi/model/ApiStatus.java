package com.bogeum.web.restapi.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonFormat(shape = Shape.OBJECT)
public enum ApiStatus {
	
	/*    일반 응답 코드    */
	SUCCESS(1, "Success"),
	
	
	/*      오류 코드     */
	// -99 ~ -1: 공통 오류
	UNKNOWN(-1, "[PATAL] UNKNOWN ERROR"),
	INVALID_STRING_PATTERN(-2, "Invalid string pattern"),
	NOT_A_NUMBER(-3, "Not a number"),
	RESOURCES_ARE_NOT_FOUND(-4, "Resources are not found"),
	API_SERVER_ERROR(-5, "API server error"),
	
	// -199 ~ -100: User account 관련 오류 
	DUPLICATED_EMAIL(-100, "Email is already used"),
	INVALID_EMAIL_PATTERN(-101, "Invalid email pattern"),
	INVALID_EMAIL_OR_PASSWORD(-102, "Invalid email or password");
	
	private int code;
	private String msg;
}
