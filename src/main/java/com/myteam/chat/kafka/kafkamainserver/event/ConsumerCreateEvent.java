package com.myteam.chat.kafka.kafkamainserver.event;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class ConsumerCreateEvent {

    private String topic;
    @Builder
    public ConsumerCreateEvent(String topic){
        this.topic=topic;
    }
}
