package com.myteam.chat.kafka.cosumeserver.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.myteam.chat.kafka.cosumeserver.exception.ErrorCode;
import com.myteam.chat.kafka.cosumeserver.exception.PlayHiveException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Controller;
import com.myteam.chat.kafka.cosumeserver.domain.ChatResponse;
import com.myteam.chat.kafka.cosumeserver.service.ChatService;
import com.myteam.chat.kafka.cosumeserver.controller.request.ChatMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import java.security.Principal;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ChatController {

	private static final String TOPIC_PREFIX = "/topic/chat.match-";
	private final ChatService chatService;
	@Qualifier("kafkaTemplate")
	private final KafkaTemplate<String,String> kafkaTemplate;
	private final ObjectMapper objectMapper;

	@MessageMapping("/send.{roomId}")
	public void sendMessage(@DestinationVariable Long roomId,
						Principal principal,
						ChatMessage message,
						StompHeaderAccessor headerAccessor) {
		try {
			log.info("Sending message to room {}: {}", roomId, message);

			String token = (String) headerAccessor.getSessionAttributes().get("token");
			// 저장 로직
			ChatResponse response = chatService.createChat(
					token, principal.getName(), message.getMessage());
			// topic 경로 생성
			String topic = TOPIC_PREFIX + roomId;
			// 해당 토픽으로 전송
			String val = objectMapper.writeValueAsString(response);
			kafkaTemplate.send(topic, val);
		}
		catch (JsonProcessingException e){
			log.info("json파싱 에러발생");
			throw new PlayHiveException(ErrorCode.INTERNAL_SERVER_ERROR
					,"서버에러 발생");
		}
	}

}
