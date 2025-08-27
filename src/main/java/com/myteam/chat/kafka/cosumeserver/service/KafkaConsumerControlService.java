package com.myteam.chat.kafka.cosumeserver.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.myteam.chat.kafka.cosumeserver.config.KafkaConsumerConfig;
import com.myteam.chat.kafka.cosumeserver.domain.ChatResponse;
import com.myteam.chat.kafka.cosumeserver.redis.service.RedisChatRoomService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.listener.MessageListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;

//@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaConsumerControlService {

   /* private final ConcurrentHashMap<String,
            List<ConcurrentMessageListenerContainer<String,String>>> consumerMap
            =new ConcurrentHashMap<>();
    private final KafkaConsumerConfig kafkaConsumerConfig;
    private final SimpMessagingTemplate simpMessagingTemplate;
    private final RedisChatRoomService redisChatRoomService;
    private final ObjectMapper objectMapper;

    public void createConsumer(String topicName,Long roomId){
        List<ConcurrentMessageListenerContainer<String,String>> data=new ArrayList<>();
        for(int i=0;3>i;i++) {
            ConcurrentMessageListenerContainer<String,String> container
                    =kafkaConsumerConfig.kafkaListenerContainerFactory()
                    .createContainer(topicName);
            container.setupMessageListener((MessageListener<String,String>) record->{
                try {
                   ChatResponse chatResponse=
                           objectMapper.readValue(record.value(), ChatResponse.class);
                   simpMessagingTemplate.convertAndSend("/room/"+roomId.toString()
                           ,chatResponse);
                }
                catch (Exception e){
                    throw new RuntimeException(e.getCause().getMessage());
                }
            });
            container.start();
            data.add(container);
        }
        consumerMap.put(topicName,data);
        log.info("consumer create success:{}",consumerMap.size());
        //redisChatRoomService.openRoom(roomId);
    }
    public void removeConsumer(String topicName,Long roomId){
        List<ConcurrentMessageListenerContainer<String,String>> consumers=
                consumerMap.get(topicName);
        consumers
                .stream()
                .forEach(x->{
                    x.stop();
                    x.destroy();
                });
        consumerMap.remove(topicName);
        log.info("consumer remove success:{}",consumerMap.size());
        //redisChatRoomService.closeRoom(roomId);
    }*/

}
