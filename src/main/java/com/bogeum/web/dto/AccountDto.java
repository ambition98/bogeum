package com.bogeum.web.dto;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {
	private int no;
	private String email;
	private String image_path;
	private boolean is_verified;
	private Timestamp regdate;
}
