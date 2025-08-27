package com.myteam.chat.kafka.kafkamainserver.domain;

import com.myteam.chat.kafka.kafkamainserver.match.match.domain.Match;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

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
