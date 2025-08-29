package com.myteam.chat.currentChatDirectory.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.myteam.chat.currentChatDirectory.domain.Chat;
import com.myteam.chat.currentChatDirectory.domain.ChatResponse;
import com.myteam.chat.currentChatDirectory.repository.ChatRepository;
import com.myteam.chat.currentChatDirectory.event.KafkaMsgSendEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaRepositoryService {
    private final ApplicationEventPublisher publisher;
    private final ChatRepository chatRepository;
    public void saveChatData(String topic,ChatResponse chatResponse) throws JsonProcessingException {
        String [] arr=topic.split("-");
        Long chatRoomId=Long.parseLong(arr[1]);
        Chat chat= Chat
                .builder()
                .chatRoomId(chatRoomId)
                .msg(chatResponse.getMessage())
                .memberId(chatResponse.getMemberId())
                .build();
        chatRepository.save(chat);
        //이벤트를 왜쓰냐면은 최소한 저장은 완벽히 진행시에 다음 로직을 진행하기위함.
        publisher.publishEvent(new KafkaMsgSendEvent(
                chatResponse,
                String.valueOf(chatRoomId)//원래 topicname인대 카프카 몼서서 그냥 이값으로했습니다.
                // 메모리 확장으로 카프카 돌릴수잇으면 추후대체 예정
        ));
    }
}
