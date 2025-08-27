package com.myteam.chat.kafka.kafkamainserver.event;


import com.myteam.chat.kafka.kafkamainserver.domain.ChatRoom;
import com.myteam.chat.kafka.kafkamainserver.service.ChatRoomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.AdminClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import java.util.Collections;
import java.util.concurrent.ExecutionException;


//@Component
//@RequiredArgsConstructor
@Slf4j
public class AdminKafkaEventHandler {


//    private final KafkaAdmin kafkaAdmin;
    //@Qualifier("adminKafkaTemplate")
  //  private final KafkaTemplate<String,String> adminKafkaTemplate;
    /*@TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void ConsumerDelControl(ConsumerDelEvent consumerDelEvent){
        try (AdminClient adminClient =
                     AdminClient.create(kafkaAdmin.getConfigurationProperties())) {
            adminClient.deleteTopics(Collections.singletonList(consumerDelEvent.getTopic())).all().get();
            log.info("Topic '{}' deleted successfully.",consumerDelEvent.getTopic());
            adminKafkaTemplate.send("del",consumerDelEvent.getTopic());
        } catch (ExecutionException | InterruptedException e) {
            log.error("Failed to delete topic '{}': {}", consumerDelEvent.getTopic(), e.getMessage());
            Thread.currentThread().interrupt(); // 인터럽트 복원
        }
    }*/
    /*@TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void ConsumerCreateControl(ConsumerCreateEvent consumerCreateEvent){
        log.info("create event:{}",consumerCreateEvent.getTopic());
        adminKafkaTemplate.send("create",consumerCreateEvent.getTopic());
    }*/

}
