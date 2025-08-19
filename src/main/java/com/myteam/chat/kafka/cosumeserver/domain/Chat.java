package com.myteam.chat.kafka.cosumeserver.domain;


import com.myteam.chat.kafka.kafkamainserver.domain.BaseTime;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor
public class Chat extends BaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private UUID memberId;
    private String msg;
    private Long chatRoomId;


    @Builder
    public Chat(UUID memberId,String msg,Long chatRoomId){
        this.chatRoomId=chatRoomId;
        this.msg=msg;
        this.memberId=memberId;
    }
}
