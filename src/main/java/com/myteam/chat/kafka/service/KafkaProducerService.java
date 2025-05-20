package com.myteam.chat.kafka.service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.myteam.chat.kafka.service.request.ChatMessage;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaProducerService {

	private final KafkaTemplate<String, ChatMessage> kafkaTemplate;

	/**
	 * Kafka로 메시지 전송
	 */
	public void sendMessage(String topic, ChatMessage chat) {
		kafkaTemplate.send(topic, chat);
		log.info("Sent message: {} to topic: {}", chat, topic);
	}
}
