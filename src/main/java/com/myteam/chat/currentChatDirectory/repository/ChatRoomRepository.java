package com.myteam.chat.currentChatDirectory.repository;


import com.myteam.chat.currentChatDirectory.domain.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {

	@Query("SELECT c FROM chat_room c WHERE c.createDate < :startOfDay")
    List<ChatRoom> findChatRoomYesterDay(@Param("startOfDay") LocalDateTime startOfDay);

	boolean existsById(Long id);
}
