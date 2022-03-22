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
@ToString(exclude = "account")
@Entity
@Table(name = "BOGEUM")
public class BogeumEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	protected BogeumEntity() {}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long no;
	
//	@Column(nullable = false)
//	private int accountNo;
	
	@Column(length = 50, nullable = false)
	private String name;
	
	@Column(nullable = false)
	private Timestamp regdate;
	
	/* ---- 연관 관계 --- */
	@ManyToOne
	@JoinColumn(name = "account_no")
	private AccountEntity account;
}
