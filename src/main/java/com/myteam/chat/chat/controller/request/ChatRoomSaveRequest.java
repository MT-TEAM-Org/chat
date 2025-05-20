package com.myteam.chat.chat.controller.request;

import lombok.Getter;

@Getter
public class ChatRoomSaveRequest {

	private final Long roomId;

	public ChatRoomSaveRequest(Long roomId) {
		this.roomId = roomId;
	}
}
