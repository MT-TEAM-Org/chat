package com.myteam.chat.kafka.cosumeserver.domain;


import com.myteam.chat.kafka.kafkamainserver.domain.BaseTime;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity(name = "chat")
@Getter
@NoArgsConstructor
public class Chat extends BaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private UUID memberId;
    @Column(nullable = false)
    private String msg;
    @Column(nullable = false)
    private Long chatRoomId;


    @Builder
    public Chat(UUID memberId,String msg,Long chatRoomId){
        this.chatRoomId=chatRoomId;
        this.msg=msg;
        this.memberId=memberId;
    }
}
