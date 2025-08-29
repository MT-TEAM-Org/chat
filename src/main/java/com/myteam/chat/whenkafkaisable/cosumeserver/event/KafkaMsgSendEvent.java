package com.myteam.chat.whenkafkaisable.cosumeserver.event;

import com.myteam.chat.currentChatDirectory.domain.ChatResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;

//@Getter
//@AllArgsConstructor
//public class KafkaMsgSendEvent {
//
//
//    private ChatResponse chatResponse;
    //메모리 확장시 string으로 바꾸기0-->카프카가 안되서 이걸로함
    //왜냐면 카프카 서버와 어려개의 채팅서버로 분리시에 카프카 메시징 큐로
    //메시지가 날아가고 채팅서버에서 파싱을 해야되는대 클래스명 에러로인해서 string타입으로
    //전달하고잇기떄문.
    //private String chatResponse;
//    private String topicName;


//}
