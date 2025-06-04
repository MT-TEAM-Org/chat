package com.myteam.chat.chat.controller;

import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import com.myteam.chat.chat.dto.response.ChatResponse;
import com.myteam.chat.chat.service.ChatService;
import com.myteam.chat.chat.controller.request.ChatMessage;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ChatController {

	private static final String TOPIC_PREFIX = "/topic/chat.match-";
	private final SimpMessagingTemplate messagingTemplate;
	private final ChatService chatService;

	@MessageMapping("/send.{category}-{roomId}")
	public void sendMessage(@DestinationVariable String category,
							@DestinationVariable Long roomId,
							ChatMessage message,
							MessageHeaders headers) {
		log.info("Sending message to room {}: {}", roomId, message);

		SimpMessageHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(headers, SimpMessageHeaderAccessor.class);
		String token = (String) accessor.getSessionAttributes().get("token");

		// 저장 로직
		ChatResponse response = chatService.createChat(
				token, message.getPublicId(), message.getMessage());

		// topic 경로 생성
		String topic = TOPIC_PREFIX + category + "-" + roomId;

		// 해당 토픽으로 전송
		messagingTemplate.convertAndSend(topic, response);
	}

}
