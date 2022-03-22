package com.bogeum.web.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Entity
@Table(name = "HASH")
public class HashEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	protected HashEntity() {}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long no;
	
	@Column(length = 64, nullable = false)
	private String digest;
	
	@Column(length = 32, nullable = false)
	private String salt;
}
