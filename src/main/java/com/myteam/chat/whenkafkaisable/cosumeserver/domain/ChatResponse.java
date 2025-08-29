package com.myteam.chat.whenkafkaisable.cosumeserver.domain;

import java.time.LocalDateTime;
import java.util.UUID;

import com.myteam.chat.currentChatDirectory.controller.request.ChatMessage;
import com.myteam.chat.currentChatDirectory.domain.UserInfo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

//@Getter
//@NoArgsConstructor
//public class ChatResponse {
//	private UUID memberId;
//	private String senderNickname;
//	private String profileImage;
//	private String message;
//	private LocalDateTime sendDate;
//
//	@Builder
//	public ChatResponse(UUID memberId,String senderNickname, String profileImage, String message, LocalDateTime sendDate) {
//		this.memberId=memberId;
//		this.senderNickname = senderNickname;
//		this.profileImage = profileImage;
//		this.message = message;
//		this.sendDate = sendDate;
//	}
//
//	public static com.myteam.chat.currentChatDirectory.domain.ChatResponse createResponse(UserInfo userInfo, ChatMessage message) {
//		return com.myteam.chat.currentChatDirectory.domain.ChatResponse.builder()
//				.memberId(userInfo.getPublicId())
//				.senderNickname(userInfo.getNickname())
//				.profileImage(userInfo.getProfileImage())
//				.message(message.getMessage())
//				.sendDate(LocalDateTime.now())
//				.build();
//	}
//	public void maskMemberId(){
//		this.memberId=null;
//	}
//
//}
