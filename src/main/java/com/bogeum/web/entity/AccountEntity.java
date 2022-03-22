package com.bogeum.web.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(exclude = {"bogeum", "bogeum_member"})
@Entity
@Table(name = "ACCOUNT")
public class AccountEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;

	protected AccountEntity() {}
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long no;
	
	@Column(length = 50, nullable = false)
	private String email;
	
	@Column(length = 200, nullable = false)
	private String imagePath;
	
	@Column(length = 1, nullable = false)
	private Boolean isVerified;
	
	@Column(length = 250, nullable = true)
	private String accessToken;
	
	@Column(length = 250, nullable = true)
	private String refreshToken;
	
	@Column(nullable = false)
	private Timestamp regdate;
	
	/* ---- 연관 관계 --- */
	@OneToMany(mappedBy = "account")
	private List<BogeumEntity> bogeum;
	
	@OneToMany(mappedBy = "account")
	private List<BogeumMemberEntity> bogeum_member;
}
