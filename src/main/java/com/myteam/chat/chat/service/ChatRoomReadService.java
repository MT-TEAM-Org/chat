package com.myteam.chat.chat.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myteam.chat.chat.domain.ChatRoom;
import com.myteam.chat.chat.repository.ChatRoomRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ChatRoomReadService {

	private final ChatRoomRepository chatRoomRepository;

	public List<ChatRoom> findChatRoomYesterDay() {
		return chatRoomRepository.findChatRoomYesterDay(LocalDate.now());
	}

	public boolean existsById(Long id) {
		return chatRoomRepository.existsById(id);
	}
}
