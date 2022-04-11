package com.bogeum.web.entity;

import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Getter
@ToString
@Entity
@Table(name = "BOGEUM")
public class BogeumEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	protected BogeumEntity() {}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long no;

	@Column(length = 50, nullable = false)
	private String name;

	@Column(nullable = false)
	private Timestamp regdate;
	
	/* ---- 연관 관계 --- */
	@ManyToOne
	@ToString.Exclude
	@JoinColumn(name = "accountNo")
	private AccountEntity account;
}
