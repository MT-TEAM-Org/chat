package com.myteam.chat.currentChatDirectory.chatroom;


import com.myteam.chat.currentChatDirectory.domain.ChatRoom;
import com.myteam.chat.currentChatDirectory.match.match.domain.Match;
import com.myteam.chat.currentChatDirectory.redis.service.RedisChatRoomService;
import com.myteam.chat.currentChatDirectory.service.ChatRoomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TopicManagementService {

    private final ChatRoomService chatRoomService;
    private final RedisChatRoomService redisChatRoomService;
    /**
    * 동적 토픽 생성
    */
   public void createTopic(Match match) {
       ChatRoom chatRoom=chatRoomService.createChatRoom(match);
       redisChatRoomService.openRoom(chatRoom.getId());
       log.info("success create chat_room:{}",chatRoom.getId());
   }

   /**
    * 동적 토픽 삭제
    */
   public void deleteTopic(String topicName) {
       String [] arr=topicName.split("-");
       ChatRoom chatRoom=chatRoomService.findChatRoom(Long.parseLong(arr[1]));
       if(chatRoom!=null){
           redisChatRoomService.closeRoom(chatRoom.getId());

       }
   }


}
