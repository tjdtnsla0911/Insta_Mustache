package com.cos.instagram.domain.user;

import java.security.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="haker")
@Entity // 이걸서야 이클래스는 JPA가 관리해줌
public class Haker {
	@Id // 기본키 설정하는듯
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	@Column(unique = true)
	String ip;
	@Column
	String attack;
	


}
