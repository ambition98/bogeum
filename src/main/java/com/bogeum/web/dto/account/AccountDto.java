package com.bogeum.web.dto.account;

import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {
	private long no;
	private String email;
	private boolean isVerified;
	private String platformName;
	private String imagePath;
	private Timestamp regdate;
}
