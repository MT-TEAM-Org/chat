package com.myteam.chat.kafka.cosumeserver.event;

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
       kafkaMsgSendEvent.getChatResponse().maskMemberId();
        simpMessagingTemplate.convertAndSend(kafkaMsgSendEvent.getTopicName()
                ,kafkaMsgSendEvent.getChatResponse());
    }
    @TransactionalEventListener(phase = TransactionPhase.AFTER_ROLLBACK)
    public void whenTransactionFail(KafkaMsgSendEvent kafkaMsgSendEvent){
        simpMessagingTemplate.convertAndSend("/user/"+kafkaMsgSendEvent.getChatResponse().getConnectionId()
        ,"에러 메시지 즉 서버 측 에러로인대 채팅이 정상적으로 ㄷ전달안도는상황.");
    }

}
