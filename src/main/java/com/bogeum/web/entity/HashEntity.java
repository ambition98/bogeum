package com.bogeum.web.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "HASH")
public class HashEntity extends AccountEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Column(length = 64, nullable = false)
	private String digest;
	
	@Column(length = 32, nullable = false)
	private String salt;
}
