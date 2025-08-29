package com.myteam.chat.currentChatDirectory.domain;

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
	@Column(name = "room_id")
	private Long id;
	private String name;

	@Builder
	public ChatRoom(Long id,String name){
		this.id=id;
		this.name=name;

	}
}
