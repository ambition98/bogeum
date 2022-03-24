package com.bogeum.util;

import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

@Component
public class CheckStringValidation {
	
	public static boolean checkEmail(String email) {
		return Pattern.matches("/^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i", email);
	}
	
	public static boolean checkPassword(String password) {
		return Pattern.matches("/^^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$/i", password);
	}
}
