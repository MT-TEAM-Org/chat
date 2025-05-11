package com.myteam.chat.chat.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myteam.chat.chat.dto.response.ChatResponse;
import com.myteam.chat.chat.service.ChatService;
import com.myteam.chat.kafka.service.request.ChatMessage;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/chat")
public class ChatController {

	private final ChatService chatService;

	@MessageMapping("/{roomId}")
	@SendTo("/room/{roomId}")
	public ResponseEntity<ChatResponse> chat(@DestinationVariable Long roomId, ChatMessage message) {
		log.info("Sending message to room {}: {}", roomId, message);

		return ResponseEntity.ok(
			chatService.createChat(roomId, message.getSender(), message.getSenderEmail(), message.getMessage()));
	}
}
