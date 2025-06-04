package com.myteam.chat.chat.controller.request;

import java.util.UUID;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ChatMessage {
	private final UUID publicId;
	private final String message;

	@Builder
	public ChatMessage(UUID publicId, String message) {
		this.publicId = publicId;
		this.message = message;
	}

	/**
	 * 채팅 생성
	 *
	 * @param publicId  보낸이
	 * @param message 내용
	 * @return Chat Entity
	 */
	public static ChatMessage createChat(UUID publicId, String message) {
		return ChatMessage.builder()
			.publicId(publicId)
			.message(message)
			.build();
	}

}
