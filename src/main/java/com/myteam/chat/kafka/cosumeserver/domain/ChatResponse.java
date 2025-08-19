package com.myteam.chat.kafka.cosumeserver.domain;

import java.time.LocalDateTime;
import java.util.UUID;

import com.myteam.chat.kafka.cosumeserver.controller.request.ChatMessage;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ChatResponse {
	private UUID memberId;
	private String connectionId;
	private String senderNickname;
	private String profileImage;
	private String message;
	private LocalDateTime sendDate;

	@Builder
	public ChatResponse(UUID memberId,String connectionId,String senderNickname, String profileImage, String message, LocalDateTime sendDate) {
		this.memberId=memberId;
		this.connectionId=connectionId;
		this.senderNickname = senderNickname;
		this.profileImage = profileImage;
		this.message = message;
		this.sendDate = sendDate;
	}

	public static ChatResponse createResponse(UserInfo userInfo, ChatMessage message) {
		return ChatResponse.builder()
				.memberId(userInfo.getPublicId())
				.connectionId(message.getConnectionId())
				.senderNickname(userInfo.getNickname())
				.profileImage(userInfo.getProfileImage())
				.message(message.getMessage())
				.sendDate(LocalDateTime.now())
				.build();
	}
	public void maskMemberId(){
		this.memberId=null;
	}

}
