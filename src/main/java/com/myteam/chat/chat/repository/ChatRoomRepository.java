package com.myteam.chat.chat.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.myteam.chat.chat.domain.ChatRoom;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {

	@Query("SELECT c FROM chat_room c WHERE c.startTime < :startOfDay")
	List<ChatRoom> findChatRoomYesterDay(@Param("startOfDay") LocalDate startOfDay);

	boolean existsById(Long id);
}
