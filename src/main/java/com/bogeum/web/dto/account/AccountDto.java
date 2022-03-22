package com.bogeum.web.dto.account;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class AccountDto {
	private int no;
	private String email;
	private String imagePath;
	private boolean isVerified;
	private Timestamp regdate;
}
