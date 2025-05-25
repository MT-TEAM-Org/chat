package com.myteam.chat.chat.domain;

import java.time.LocalDateTime;

import com.myteam.chat.global.domain.BaseTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "chat_room")
public class ChatRoom extends BaseTime {

	@Id
	private Long id;

	@Column(name = "start_time")
	private LocalDateTime startTime;

	@Builder
	public ChatRoom(Long id, LocalDateTime startTime) {
		this.id = id;
		this.startTime = startTime;
	}
}
