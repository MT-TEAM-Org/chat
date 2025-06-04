package com.myteam.chat.chat.dto.response;

import java.time.LocalDateTime;
import java.util.UUID;

import com.myteam.chat.chat.domain.UserInfo;
import com.myteam.chat.chat.controller.request.ChatMessage;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ChatResponse {
	private final UUID publicId;
	private final String senderNickname;
	private final String profileImage;
	private final String message;

	private final LocalDateTime sendDate;

	@Builder
	public ChatResponse(UUID publicId, String senderNickname, String profileImage, String message, LocalDateTime sendDate) {
		this.publicId = publicId;
		this.senderNickname = senderNickname;
		this.profileImage = profileImage;
		this.message = message;
		this.sendDate = sendDate;
	}

	public static ChatResponse createResponse(UserInfo userInfo, ChatMessage message) {
		return ChatResponse.builder()
				.publicId(userInfo.getPublicId())
				.senderNickname(userInfo.getNickname())
				.profileImage(userInfo.getProfileImage())
				.message(message.getMessage())
				.sendDate(LocalDateTime.now())
				.build();
	}
}
