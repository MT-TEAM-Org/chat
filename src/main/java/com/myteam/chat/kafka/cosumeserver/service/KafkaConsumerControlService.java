package com.myteam.chat.kafka.cosumeserver.service;


import com.myteam.chat.kafka.cosumeserver.config.KafkaConsumerConfig;
import com.myteam.chat.kafka.cosumeserver.redis.service.RedisChatRoomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.listener.MessageListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class KafkaConsumerControlService {

    private final ConcurrentHashMap<String,
            List<ConcurrentMessageListenerContainer<String,String>>> consumerMap
            =new ConcurrentHashMap<>();
    private final KafkaConsumerConfig kafkaConsumerConfig;
    private final KafkaRepositoryService kafkaRepositoryService;
    private final RedisChatRoomService redisChatRoomService;

    public void createConsumer(String topicName,Long roomId){
        List<ConcurrentMessageListenerContainer<String,String>> data=new ArrayList<>();
        for(int i=0;3>i;i++) {
            ConcurrentMessageListenerContainer<String,String> container
                    =kafkaConsumerConfig.kafkaListenerContainerFactory()
                    .createContainer(topicName);
            container.setupMessageListener((MessageListener<String,String>) record->{
                log.info("msg:{}",record.value());
                try {
                    kafkaRepositoryService.saveChatData(record.topic(), record.value());
                }
                catch (Exception e){
                    throw new RuntimeException(e.getCause().getMessage());
                }
            });
            container.start();
            data.add(container);
        }
        consumerMap.put(topicName,data);
        redisChatRoomService.openRoom(roomId);
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
        redisChatRoomService.closeRoom(roomId);
    }

}
