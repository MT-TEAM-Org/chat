package com.myteam.chat.kafka.kafkamainserver.service;

import java.util.Collections;
import java.util.concurrent.ExecutionException;

import com.myteam.chat.kafka.cosumeserver.redis.service.RedisChatRoomService;
import com.myteam.chat.kafka.kafkamainserver.domain.ChatRoom;
import com.myteam.chat.kafka.kafkamainserver.event.ConsumerCreateEvent;
import com.myteam.chat.kafka.kafkamainserver.event.ConsumerDelEvent;
import com.myteam.chat.kafka.kafkamainserver.match.match.domain.Match;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;


@Slf4j
@Service
@RequiredArgsConstructor
public class TopicManagementService {

   //private final KafkaAdmin kafkaAdmin;
   private final ChatRoomService chatRoomService;
   //private final ApplicationEventPublisher publisher;
    private static final String TOPIC_PREFIX = "/topic/chat.match-";
    private final RedisChatRoomService redisChatRoomService;
    /**
    * 동적 토픽 생성
    */
   public void createTopic(Match match) {
       //ChatRoom chatRoom=null;
       ChatRoom chatRoom=chatRoomService.createChatRoom(match);
       ConsumerCreateEvent consumerControlEvent=ConsumerCreateEvent
               .builder()
               .topic(TOPIC_PREFIX+chatRoom.getId())
               .build();
       log.info("success create chat_room:{}",chatRoom.getId());
       redisChatRoomService.openRoom(chatRoom.getId());
       //publisher.publishEvent(consumerControlEvent);
       /*try (AdminClient adminClient = AdminClient.create(kafkaAdmin.getConfigurationProperties())) {
           chatRoom=chatRoomService.createChatRoom(match);
           NewTopic topic = TopicBuilder
                   .name(TOPIC_PREFIX+chatRoom.getId())
                           .partitions(3)
                                   .build();
           adminClient.createTopics(Collections.singletonList(topic)).all().get();
           log.info("Topic '{}' created successfully with {} partitions and replication factor {}.",
                   topic.name(),topic.numPartitions(),1);
           ConsumerCreateEvent consumerControlEvent=ConsumerCreateEvent
                   .builder()
                   .topic(TOPIC_PREFIX+chatRoom.getId())
                   .build();
           publisher.publishEvent(consumerControlEvent);
       } catch (ExecutionException | InterruptedException e) {
           log.error("Failed to create topic about match '{}': {}", match.getId(), e.getMessage());
           Thread.currentThread().interrupt(); // 인터럽트 복원
           if(chatRoom!=null) {
               chatRoomService.eraseChatRoom(chatRoom.getId());
           }
       }*/
   }

   /**
    * 동적 토픽 삭제
    */
   public void deleteTopic(String topicName) {
       String [] arr=topicName.split("-");
       ChatRoom chatRoom=chatRoomService.findChatRoom(Long.parseLong(arr[1]));
       if(chatRoom!=null){
           redisChatRoomService.closeRoom(chatRoom.getId());
           /*ConsumerDelEvent consumerControlEvent= ConsumerDelEvent
                   .builder()
                   .topic(topicName)
                   .build();*/
          // publisher.publishEvent(consumerControlEvent);
       }
   }
   /*public void deleteTopic(String topicName) {
       try (AdminClient adminClient = AdminClient.create(kafkaAdmin.getConfigurationProperties())) {
           adminClient.deleteTopics(Collections.singletonList(topicName)).all().get();
           log.info("Topic '{}' deleted successfully.", topicName);
           String [] arr=topicName.split("-");
           ChatRoom chatRoom=chatRoomService.findChatRoom(Long.parseLong(arr[1]));
           if(chatRoom!=null){
               chatRoom.updateClosed();
               ConsumerControlEvent consumerControlEvent=
                   new ConsumerControlEvent("del",TOPIC_PREFIX+chatRoom.getId());
               publisher.publishEvent(consumerControlEvent);
           }
       } catch (ExecutionException | InterruptedException e) {
           log.error("Failed to delete topic '{}': {}", topicName, e.getMessage());
           Thread.currentThread().interrupt(); // 인터럽트 복원
       }
   }*/




}
