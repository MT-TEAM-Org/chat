package com.myteam.chat.chat.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.myteam.chat.chat.service.request.Chat;
import com.myteam.chat.chat.service.request.ChatMessage;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaConsumerService {
   // private final SimpMessagingTemplate messagingTemplate;

   @KafkaListener(topicPattern = "room-.*", groupId = "chat-group", containerFactory = "kafkaListenerContainerFactory")
   public void consumeMessage(ConsumerRecord<String, ChatMessage> record) {
       String topic = record.topic();
       ChatMessage message = record.value();

       log.info("Consumed message from topic {}: {}", topic, message);

       // WebSocket으로 클라이언트에게 메시지 전달
       String roomId = topic.split("-")[1];
       // messagingTemplate.convertAndSend("/topic/room/" + roomId, chat);
   }
}
