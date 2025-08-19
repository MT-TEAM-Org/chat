package com.myteam.chat.kafka.kafkamainserver.event;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class ConsumerCreateEvent {
    private String create;
    private String topic;
    @Builder
    public ConsumerCreateEvent(String create, String topic){
        this.create=create;
        this.topic=topic;
    }
}
