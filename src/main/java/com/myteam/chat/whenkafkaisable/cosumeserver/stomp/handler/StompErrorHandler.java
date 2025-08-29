package com.myteam.chat.whenkafkaisable.cosumeserver.stomp.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.StompSubProtocolErrorHandler;

import java.nio.charset.StandardCharsets;

/*@Component
@Slf4j
public class StompErrorHandler extends StompSubProtocolErrorHandler {
    @Override
    public Message handleClientMessageProcessingError(Message clientMessage, Throwable ex) {
        StompHeaderAccessor headerAccessor=StompHeaderAccessor.create(StompCommand.ERROR);
        headerAccessor.setMessage(ex.getCause().getMessage());
        headerAccessor.setLeaveMutable(true);
        log.info("error:{}",ex.getCause().getClass());
        log.info("header:{}",headerAccessor.getMessageHeaders());
        return MessageBuilder.createMessage(ex.getCause().getMessage().getBytes(StandardCharsets.UTF_8)
                ,headerAccessor.getMessageHeaders());
    }
}*/
