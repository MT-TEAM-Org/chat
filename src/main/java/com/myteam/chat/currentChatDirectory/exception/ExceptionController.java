package com.myteam.chat.currentChatDirectory.exception;

import com.myteam.chat.whenkafkaisable.cosumeserver.exception.PlayHiveException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.security.Principal;

@ControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class ExceptionController {

    private final SimpMessagingTemplate simpMessagingTemplate;

    //stomp 인터셉터를 통과하였으나 아직 producer로 넘어가지않은 상황속에서 발생한 에러의 경우 이렇게컨트롤.
    //카프카로 넘어가는순간 다른 스택이라서 애로는 에러가 컨트롤이안된다.
    @MessageExceptionHandler(PlayHiveException.class)
    public void playHiveEx(Exception e, Principal principal) {
        String des="/error/"+principal.getName();
        simpMessagingTemplate.convertAndSend(
                des,e.getMessage());
    }
}
