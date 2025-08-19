package com.myteam.chat.kafka.cosumeserver.exception;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.security.Principal;

@ControllerAdvice
@RequiredArgsConstructor
public class ExceptionController {

    private final SendStompError sendStompError;

    //stomp 인터셉터를 통과하였으나 아직 producer로 넘어가지않은 상황속에서 발생한 에러의 경우 이렇게컨트롤.
    //카프카로 넘어가는순간 다른 스택이라서 애로는 에러가 컨트롤이안된다.
    @MessageExceptionHandler(PlayHiveException.class)
    public void playHiveEx(Exception e, Principal principal) {
        sendStompError.sendErrorMsgPersonal(principal.getName(),e.getMessage());
    }
}
