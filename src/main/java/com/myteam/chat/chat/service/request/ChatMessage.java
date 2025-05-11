package com.myteam.chat.chat.service.request;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ChatMessage {
	private final String sender;

	private final String senderEmail;

	private final String message;

	private final LocalDateTime sendDate;

	@Builder
	public ChatMessage(String sender, String senderEmail, String message, LocalDateTime sendDate) {
		this.sender = sender;
		this.senderEmail = senderEmail;
		this.message = message;
		this.sendDate = sendDate;
	}

	/**
	 * 채팅 생성
	 *
	 * @param sender  보낸이
	 * @param message 내용
	 * @return Chat Entity
	 */
	public static ChatMessage createChat(String sender, String senderEmail, String message) {
		return ChatMessage.builder()
			.sender(sender)
			.senderEmail(senderEmail)
			.message(message)
			.build();
	}

}
