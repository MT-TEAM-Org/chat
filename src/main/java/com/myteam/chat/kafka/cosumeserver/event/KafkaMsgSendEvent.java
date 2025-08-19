package com.myteam.chat.kafka.cosumeserver.event;

import com.myteam.chat.kafka.cosumeserver.domain.ChatResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class KafkaMsgSendEvent {

    private ChatResponse chatResponse;
    private String topicName;


}
