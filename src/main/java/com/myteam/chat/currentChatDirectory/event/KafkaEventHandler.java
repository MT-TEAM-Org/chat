package com.myteam.chat.currentChatDirectory.event;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
@Slf4j
public class KafkaEventHandler {

    private final SimpMessagingTemplate simpMessagingTemplate;
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void whenTransactionSuccess(KafkaMsgSendEvent kafkaMsgSendEvent){
        log.info("after commit start send message");
        simpMessagingTemplate.convertAndSend("/room/"+kafkaMsgSendEvent.getTopicName()
                ,kafkaMsgSendEvent.getChatResponse());
    }
}
