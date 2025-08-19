package com.myteam.chat.kafka.cosumeserver.repository;

import com.myteam.chat.kafka.cosumeserver.domain.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepository extends JpaRepository<Chat,Long> {
}
