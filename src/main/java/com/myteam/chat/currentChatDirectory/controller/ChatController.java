package com.myteam.chat.currentChatDirectory.controller;


import com.myteam.chat.currentChatDirectory.exception.ErrorCode;
import com.myteam.chat.currentChatDirectory.exception.PlayHiveException;
import com.myteam.chat.currentChatDirectory.service.KafkaRepositoryService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Controller;
import com.myteam.chat.currentChatDirectory.domain.ChatResponse;
import com.myteam.chat.currentChatDirectory.service.ChatService;
import com.myteam.chat.currentChatDirectory.controller.request.ChatMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;


@Slf4j
@Controller
@RequiredArgsConstructor
@Transactional
public class ChatController {

	private static final String TOPIC_PREFIX = "topic_chat.match-";
	private final ChatService chatService;
	private final KafkaRepositoryService kafkaRepositoryService;
	@Value("${spring.datasource.url}")
	private String db_source_name;

	@MessageMapping("/send.{roomId}")
	public void sendMessage(@DestinationVariable(value = "roomId") Long roomId,
						ChatMessage message,
						StompHeaderAccessor headerAccessor) {
		try {
			log.info("Sending message to room {}: {}", roomId, message);
			String token = (String) headerAccessor.getSessionAttributes().get("token");
			// 저장 로직
			ChatResponse response = chatService.createChat(
					token,message.getMessage());
			// topic 경로 생성
			String topic = TOPIC_PREFIX + roomId;
			// 해당 토픽으로 전송
	
			kafkaRepositoryService.saveChatData(topic,response);
		}
		catch (Exception e){
			log.info("메시지 전달중에러 발생:{}",e.getMessage());
			throw new PlayHiveException(ErrorCode.INTERNAL_SERVER_ERROR
					,"서버에러 발생");
		}
	}

}
