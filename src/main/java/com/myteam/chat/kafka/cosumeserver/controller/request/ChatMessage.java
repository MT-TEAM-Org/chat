package com.myteam.chat.kafka.cosumeserver.controller.request;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ChatMessage {
	private final String connectionId;
	private final String message;

	@Builder
	public ChatMessage(String connectionId, String message) {
		this.connectionId=connectionId;
		this.message = message;
	}

	/**
	 * 채팅 생성
	 *
	 * @param connectionId 현재 접속자가 유지중인 connectionId
	 * @param message 내용
	 * @return Chat Entity
	 */
	public static ChatMessage createChat(String connectionId, String message) {
		return ChatMessage.builder()
			.connectionId(connectionId)
			.message(message)
			.build();
	}

}
