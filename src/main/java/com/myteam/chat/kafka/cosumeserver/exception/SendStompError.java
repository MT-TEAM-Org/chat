package com.myteam.chat.kafka.cosumeserver.exception;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class SendStompError {

    private final SimpMessagingTemplate simpMessagingTemplate;
    public void  sendErrorMsgPersonal(String connectionId,String errorMsg){
        StompHeaderAccessor accessor=StompHeaderAccessor.create(StompCommand.ERROR);
        MessageHeaders headers = accessor.getMessageHeaders();
        simpMessagingTemplate.convertAndSend(
                "/user/" + connectionId,errorMsg,headers);
    }
    public void  sendErrorMsgGlobal(String connectionId,String errorMsg){
        StompHeaderAccessor accessor=StompHeaderAccessor.create(StompCommand.ERROR);
        MessageHeaders headers = accessor.getMessageHeaders();
        simpMessagingTemplate.convertAndSend(
                "/user/" + connectionId,errorMsg,headers);
    }
}
