package com.bogeum.util;

import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

@Component
public class CheckStringValidation {
	
	public boolean checkValidEmailPattern(String email) {
		return Pattern.matches("/^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i", email);
	}
}
