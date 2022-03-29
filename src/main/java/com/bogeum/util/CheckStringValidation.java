package com.bogeum.util;

import java.util.regex.Pattern;

public class CheckStringValidation {
	
	public static boolean checkEmail(String email) {
		return Pattern.matches("\\w+@\\w+\\.\\w+(\\.\\w+)?", email);
	}
	
	public static boolean checkPassword(String password) {
		return Pattern.matches("(?=.*[!@#$%&*?])(?=.*[a-zA-Z])(?=.*\\d)[a-zA-Z!@#$%&*?\\d]{8,}", password);
	}
}
