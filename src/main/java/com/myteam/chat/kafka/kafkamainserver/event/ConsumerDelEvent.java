package com.myteam.chat.kafka.kafkamainserver.event;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ConsumerDelEvent {
    private String del;
    private String topic;;


    @Builder
    public ConsumerDelEvent(String del, String topic){
        this.del=del;
        this.topic=topic;
    }
}
