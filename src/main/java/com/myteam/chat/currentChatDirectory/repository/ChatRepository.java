package com.myteam.chat.currentChatDirectory.repository;

import com.myteam.chat.currentChatDirectory.domain.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepository extends JpaRepository<Chat,Long> {
}
