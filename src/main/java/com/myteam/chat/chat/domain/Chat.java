package com.myteam.chat.chat.domain;

import java.time.LocalDateTime;

import com.myteam.chat.global.domain.BaseTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Chat extends BaseTime {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String sender;

	@Column(name = "sender_email")
	private String senderEmail;

	private String message;

	@Column(name = "send_date")
	private LocalDateTime sendDate;

	@Builder
	public Chat(String sender, String senderEmail, String message, LocalDateTime sendDate) {
		this.sender = sender;
		this.senderEmail = senderEmail;
		this.message = message;
		this.sendDate = sendDate;
	}
}
