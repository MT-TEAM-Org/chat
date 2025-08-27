package com.myteam.chat.kafka.kafkamainserver.event;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ConsumerDelEvent {

    private String topic;;


    @Builder
    public ConsumerDelEvent( String topic){
        this.topic=topic;
    }
}
