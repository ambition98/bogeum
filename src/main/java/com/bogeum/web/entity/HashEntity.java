package com.bogeum.web.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Getter
@Setter
@ToString
@Entity
@Table(name = "HASH")
@DynamicInsert
public class HashEntity extends AccountEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Column(length = 60, nullable = false)
	private String digest;
}
