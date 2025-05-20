package com.myteam.chat.chat.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class ChatRoom {

	@Id
	private Long id;

	@Builder
	public ChatRoom(Long id) {
		this.id = id;
	}
}
