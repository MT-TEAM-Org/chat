package com.myteam.chat.currentChatDirectory.controller.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ChatMessage {
	private  String message;

	@Builder
	public ChatMessage( String message) {
		this.message = message;
	}

	/**
	 * 채팅 생성
	 * @param message 내용
	 * @return Chat Entity
	 */
	public static ChatMessage createChat( String message) {
		return ChatMessage.builder()
			.message(message)
			.build();

	}

}
