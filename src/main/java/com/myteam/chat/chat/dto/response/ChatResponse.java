package com.myteam.chat.chat.dto.response;

import java.time.LocalDateTime;

import com.myteam.chat.kafka.service.request.ChatMessage;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ChatResponse {
	private final String sender;

	private final String senderEmail;

	private final String message;

	private final LocalDateTime sendDate;

	@Builder
	public ChatResponse(String sender, String senderEmail, String message, LocalDateTime sendDate) {
		this.sender = sender;
		this.senderEmail = senderEmail;
		this.message = message;
		this.sendDate = sendDate;
	}

	public static ChatResponse createResponse(ChatMessage message) {
		return ChatResponse.builder()
			.sender(message.getSender())
			.senderEmail(message.getSenderEmail())
			.message(message.getMessage())
			.sendDate(message.getSendDate())
			.build();
	}
}
