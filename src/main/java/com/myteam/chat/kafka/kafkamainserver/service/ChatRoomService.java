package com.myteam.chat.kafka.kafkamainserver.service;


import com.myteam.chat.kafka.kafkamainserver.repository.ChatRoomRepository;
import com.myteam.chat.kafka.kafkamainserver.domain.ChatRoom;
import com.myteam.chat.kafka.kafkamainserver.match.match.domain.Match;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;
    public ChatRoom createChatRoom(Match match){
        ChatRoom chatRoom=ChatRoom
                .builder()
                .id(match.getId())
                .name(match.getLeagueName())
                .build();
        chatRoom=chatRoomRepository.save(chatRoom);
        return chatRoom;
    }
    public ChatRoom findChatRoom(Long id){
        return chatRoomRepository.findById(id).orElse(null);
    }
    public void eraseChatRoom(Long id){
        chatRoomRepository.deleteById(id);
    }

}
