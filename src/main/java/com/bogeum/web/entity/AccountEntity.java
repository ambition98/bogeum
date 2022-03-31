package com.bogeum.web.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(exclude = {"bogeum", "bogeumMember"})
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "DTYPE")
@Table(name = "ACCOUNT")
@DynamicInsert
public class AccountEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long no;
	
	@Column(length = 50, nullable = false)
	private String email;
	
	@Column(length = 200)
	private String imagePath;
	
//	@Column(length = 1, nullable = true, columnDefinition = "boolean default false")
	@ColumnDefault("false")
	@Column(length = 1)
	// 실제 테이블은 not null이나, default 값이 정해져 있다.
	// 따라서 insert 하기 위해선 DynamicInsert를 이용하여 null 값을 넣을 시
	// insert 할 컬럼에서 제외하고 실제 테이블에는 default값이 들어가게 된다.
	// not null 이면서 default값이 정해진 컬럼에는 전부 이와같이 적용
	// 그런데 이러면 entity기반으로 테이블이 생성되는 h2db와 배포환경 db의 테이블 정의가 달라지는데?? 문제가 있을 것 같다.
	private Boolean isVerified;
	
	@Column(length = 50)
	private String platformName;
	
	@Column(length = 250)
	private String accessToken;
	
	@Column(length = 250)
	private String refreshToken;
	
	@ColumnDefault("CURRENT_TIMESTAMP")
	private Timestamp regdate;
	
	/* ---- 연관 관계 --- */
	@OneToMany(mappedBy = "account")
	private List<BogeumEntity> bogeum;
	
	@OneToMany(mappedBy = "account")
	private List<BogeumMemberEntity> bogeumMember;
}
