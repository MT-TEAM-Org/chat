package com.myteam.chat.chat.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myteam.chat.chat.domain.BadWordFilter;
import com.myteam.chat.chat.dto.response.ChatResponse;
import com.myteam.chat.kafka.service.KafkaProducerService;
import com.myteam.chat.kafka.service.request.ChatMessage;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class ChatService {

	private static final String ROOT_TOPIC_NAME = "room-";
	private static final String ROOT_ELASTICSEARCH_TOPIC_NAME = "room-elasticsearch-";

	private final KafkaProducerService kafkaProducerService;
	private final BadWordFilter badWordFilter;

	/**
	 * 채팅 생성
	 */
	public ChatResponse createChat(Long roomId, String sender, String senderEmail, String message) {
		message = validateAndTrimMessage(message);

		String filteredMessage = badWordFilter.filterMessage(message);
		ChatMessage chat = ChatMessage.createChat(sender, senderEmail, filteredMessage);

		// Kafka에 메시지 전송
		kafkaProducerService.sendMessage(ROOT_TOPIC_NAME + roomId, chat);
		kafkaProducerService.sendMessage(ROOT_ELASTICSEARCH_TOPIC_NAME + roomId, chat);

		return ChatResponse.createResponse(chat);
	}

	public String validateAndTrimMessage(String message) {
		if (message != null && message.length() > 500) {
			log.warn("Message length exceeds 500 characters. Trimming the message.");
			return message.substring(0, 500);
		}
		return message;
	}
}
