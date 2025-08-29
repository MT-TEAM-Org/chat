package com.myteam.chat.whenkafkaisable.cosumeserver.event;

import com.myteam.chat.currentChatDirectory.event.KafkaMsgSendEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

//@Component
//@RequiredArgsConstructor
//@Slf4j
//public class KafkaEventHandler {
//    Qualifier(value ="kafkaConsumeTemplate")
//    private final KafkaTemplate<String,String> kafkaTemplate;
//    private final SimpMessagingTemplate simpMessagingTemplate;
//    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
//    public void whenTransactionSuccess(KafkaMsgSendEvent kafkaMsgSendEvent){
//        log.info("after commit start send message");
//        simpMessagingTemplate.convertAndSend("/room/"+kafkaMsgSendEvent.getTopicName()
//                ,kafkaMsgSendEvent.getChatResponse());
//        kafkaTemplate.send(kafkaMsgSendEvent.getTopicName(),kafkaMsgSendEvent.getChatResponse());
//    }
//}
