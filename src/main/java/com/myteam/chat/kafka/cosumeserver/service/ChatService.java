package com.myteam.chat.kafka.cosumeserver.service;

import com.myteam.chat.kafka.cosumeserver.exception.ErrorCode;
import com.myteam.chat.kafka.cosumeserver.exception.PlayHiveException;
import com.myteam.chat.kafka.cosumeserver.redis.service.RedisUserInfoService;
import com.myteam.chat.kafka.cosumeserver.domain.UserInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myteam.chat.kafka.cosumeserver.BadWordFilter;
import com.myteam.chat.kafka.cosumeserver.domain.ChatResponse;
//import com.myteam.chat.kafka.service.KafkaProducerService;
import com.myteam.chat.kafka.cosumeserver.controller.request.ChatMessage;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class ChatService {

	private static final String ROOT_TOPIC_NAME = "room-";

	private final BadWordFilter badWordFilter;
	private final RedisUserInfoService redisUserInfoService;

	/**
	 * 채팅 생성
	 */
	public ChatResponse createChat(String token, String connectionId, String message) {
		message = validateAndTrimMessage(message);

		String filteredMessage = badWordFilter.filterMessage(message);
		ChatMessage chat = ChatMessage.createChat(connectionId, filteredMessage);

		// redis에서 유저 정보 가져오기
		UserInfo userInfo = redisUserInfoService.getUserInfo(token)
				.orElseThrow(() -> new PlayHiveException(ErrorCode.INVALID_TOKEN));
		return ChatResponse.createResponse(userInfo, chat);
	}

	private String validateAndTrimMessage(String message) {
		if (message != null && message.length() > 500) {
			log.warn("Message length exceeds 500 characters. Trimming the message.");
			return message.substring(0, 500);
		}
		return message;
	}
}
