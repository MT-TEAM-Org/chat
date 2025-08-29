package com.myteam.chat.whenkafkaisable.cosumeserver.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

//@Service
//@RequiredArgsConstructor
//@Slf4j
//public class KafkaConsumerControlService {

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

//}
