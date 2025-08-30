package com.myteam.chat.currentChatDirectory.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.myteam.chat.currentChatDirectory.domain.ChatRoom;
import com.myteam.chat.currentChatDirectory.repository.ChatRoomRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ChatRoomReadService {

	private final ChatRoomRepository chatRoomRepository;

	public List<ChatRoom> findChatRoomYesterDay() {
		return chatRoomRepository.findChatRoomYesterDay(LocalDateTime.now());
	}

	public boolean existsById(Long id) {
		return chatRoomRepository.existsById(id);
	}
}
