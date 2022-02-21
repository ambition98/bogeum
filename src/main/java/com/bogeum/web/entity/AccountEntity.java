package com.bogeum.web.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "account")
@Getter
@Setter
@ToString
public class AccountEntity implements Serializable {
	
	private static final long serialVerionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int no;
	
	@Column(length = 64)
	private String email;
	
	@Column(length = 256)
	private String image_path;
	
	@Column(length = 1)
	boolean is_verified;
	
	@Column(length = 256)
	private String accessToken;
	
	@Column(length = 256)
	private String refreshToken;
	
	private Timestamp regdate;
}
