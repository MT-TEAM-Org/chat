package com.myteam.chat.kafka.cosumeserver.stomp.handler;

import com.myteam.chat.kafka.cosumeserver.redis.service.RedisChatRoomService;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.myteam.chat.kafka.cosumeserver.exception.ErrorCode;
import com.myteam.chat.kafka.cosumeserver.exception.PlayHiveException;
//import com.myteam.chat.global.jwt.JwtProvider;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Component
@Order(Ordered.HIGHEST_PRECEDENCE + 99)
public class StompInBoundHandler implements ChannelInterceptor {

	private final RedisChatRoomService redisChatRoomService;

	@Override
	public Message<?> preSend(Message<?> message, MessageChannel messageChannel) {

		StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
		StompCommand command = accessor.getCommand();
		if(command!=null&&command.equals(StompCommand.DISCONNECT)){
			return message;
		}
		String roomId=getRoomId(accessor);
		if(roomId==null||!redisChatRoomService.checkRoom(Long.valueOf(roomId))){
			throw new PlayHiveException(ErrorCode.INVALID_CHATROOM);
		}
		String authorizationHeader = getAuthorizationHeader(accessor);
		if (authorizationHeader == null || authorizationHeader.isEmpty()) {
			log.warn("Authorization header missing or not Bearer type: {}", authorizationHeader);
			throw new PlayHiveException(ErrorCode.MISSING_AUTH_HEADER);
		}
		handleConnect(accessor, authorizationHeader);

		return message;
	}

	/**
	 * STOMP CONNECT 프레임 처리 로직
	 */
	private void handleConnect(StompHeaderAccessor accessor, String authorizationHeader) {
		if (!authorizationHeader.startsWith("[Bearer ")) {
			log.warn("Authorization header missing or not Bearer type: {}", authorizationHeader);
			throw new PlayHiveException(ErrorCode.MISSING_AUTH_HEADER);
		}

		String token = extractToken(authorizationHeader);

		// 세션에 token 저장
		accessor.getSessionAttributes().put("token", token);
		log.info("Authenticated user connected: {}", token);
	}

	/**
	 * STOMP 헤더에서 "Authorization" 값을 얻는다.
	 */
	private String getAuthorizationHeader(StompHeaderAccessor accessor) {
		String authHeaders = String.valueOf(accessor.getNativeHeader("Authorization"));
		if (authHeaders == null || authHeaders.isEmpty()) {
			return null;
		}
		return authHeaders;
	}

	/**
	 * "[Bearer xxxxx]" 형태에서 실제 토큰 부분만 추출
	 */
	private String extractToken(String authorizationHeader) {
		String[] parts = authorizationHeader.split(" ");
		if (parts.length < 2) {
			throw new PlayHiveException(ErrorCode.MISSING_AUTH_HEADER);
		}
		String rawToken = parts[1];
		return rawToken.substring(0, rawToken.length() - 1); // "]" 제거
	}

	private String getRoomId(StompHeaderAccessor accessor){
		String roomId=accessor.getDestination();
		if(roomId==null){
			return null;
		}
		return roomId.split("-")[1];
	}
}
