package com.myteam.chat.kafka.kafkamainserver.domain;

import com.myteam.chat.kafka.kafkamainserver.match.match.domain.Match;
import jakarta.persistence.*;
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
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name ="match_id")
	private Match match;
	@Column
	private Boolean closed=false;

	@Builder
	public ChatRoom(Long id,Match match){
		this.id=id;
		this.match=match;
	}
	public void updateClosed(){
		this.closed=true;
	}
	public void cancleClosed(){
		this.closed=false;
	}
}
