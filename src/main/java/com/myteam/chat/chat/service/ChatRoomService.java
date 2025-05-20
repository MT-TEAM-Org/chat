package com.myteam.chat.chat.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myteam.chat.chat.domain.ChatRoom;
import com.myteam.chat.chat.repository.ChatRoomRepository;
import com.myteam.chat.kafka.service.TopicManagementService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class ChatRoomService {

	private static final String ROOM_TOPIC = "room-";

	private final ChatRoomRepository chatRoomRepository;
	private final TopicManagementService topicManagementService;

	public Long createChatRoom(Long id) {
		ChatRoom chatRoom = chatRoomRepository.save(ChatRoom.builder().id(id).build());
		topicManagementService.createTopic(ROOM_TOPIC + chatRoom.getId(), 3, (short)1);
		return chatRoom.getId();
	}

}
