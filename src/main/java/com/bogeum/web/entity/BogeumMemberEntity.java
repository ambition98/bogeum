package com.bogeum.web.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(exclude = {"account", "bogeum"})
@Entity
@Table(name = "BOGEUM_MEMBER")
public class BogeumMemberEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	protected BogeumMemberEntity() {}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long no;
	
//	@Column(nullable = false)
//	private int bogeum_no;
//	
//	@Column(nullable = false)
//	private int account_no;
	
	@Column(length = 50, nullable = false)
	private String name;
	
	@Column(nullable = false)
	private Timestamp regdate;
	
	/* ---- 연관 관계 --- */
	@ManyToOne
	@JoinColumn(name = "account_no")
	private AccountEntity account;
	
	@ManyToOne
	@JoinColumn(name = "bougem_no")
	private BogeumEntity bogeum;
}
