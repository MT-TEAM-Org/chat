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


@Component
@RequiredArgsConstructor
@Slf4j
public class KafkaEventHandler {

    @Qualifier("adminKafkaTemplate")
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final KafkaAdmin kafkaAdmin;
    private final ChatRoomService chatRoomService;
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    //after commit으로 작동하는 이벤트라서 이벤트를 호출한애의 트랜잭션은 현재 닫힌상태
    //엄밀히 말하면 db와의 트랜잭션이 닫힌거다. 그렇기에 여기서 chatroom의 상태를 업데이트하고
    //db에 저장하기위해선 새로운 트랜잭션을 형성해줘야한다.
    public void ConsumerDelControl(ConsumerDelEvent consumerDelEvent){
        try (AdminClient adminClient =
                     AdminClient.create(kafkaAdmin.getConfigurationProperties())) {
            adminClient.deleteTopics(Collections.singletonList(consumerDelEvent.getTopic())).all().get();
            log.info("Topic '{}' deleted successfully.",consumerDelEvent.getTopic());
            kafkaTemplate.send(consumerDelEvent.getDel(),consumerDelEvent.getTopic());
        } catch (ExecutionException | InterruptedException e) {
            log.error("Failed to delete topic '{}': {}", consumerDelEvent.getTopic(), e.getMessage());
            Thread.currentThread().interrupt(); // 인터럽트 복원
            String [] arr=consumerDelEvent.getTopic().split("-");
            ChatRoom chatRoom=
                    chatRoomService.findChatRoom(Long.parseLong(arr[1]));
            chatRoom.cancleClosed();
        }
    }
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void ConsumerCreateControl(ConsumerCreateEvent consumerEvent){
        kafkaTemplate.send(consumerEvent.getCreate(),consumerEvent.getTopic());
    }
}
