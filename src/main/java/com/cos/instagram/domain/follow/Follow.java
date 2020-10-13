package com.cos.instagram.domain.follow;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.cos.instagram.domain.user.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="follow")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Follow {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name="fromUserId")
	private User fromUser;
	
	@ManyToOne
	@JoinColumn(name="toUserId")
	private User toUser;
	
	@CreationTimestamp
	private Timestamp createDate;
	
	@UpdateTimestamp
	private Timestamp updateDate;
}






