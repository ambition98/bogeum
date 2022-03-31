package com.bogeum.web.dto.account;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AccountSignDto {
	private String email;
	private String passwd;
}
