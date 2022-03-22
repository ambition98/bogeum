package com.bogeum.web.dto.account;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class AccountSignupDto {
	private String email;
	private String passwd;
	private String passwdrp;
}
