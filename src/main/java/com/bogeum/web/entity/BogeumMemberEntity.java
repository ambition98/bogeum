package com.bogeum.web.entity;

import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Getter
@ToString
@Entity
@Table(name = "BOGEUM_MEMBER")
public class BogeumMemberEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	protected BogeumMemberEntity() {}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long no;
	
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
	@ToString.Exclude
	@JoinColumn(name = "account_no")
	private AccountEntity account;
	
	@ManyToOne
	@ToString.Exclude
	@JoinColumn(name = "bougem_no")
	private BogeumEntity bogeum;
}
