package com.myteam.chat.kafka.cosumeserver;

import com.myteam.chat.kafka.cosumeserver.service.KafkaConsumerControlService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

//@Service
//@RequiredArgsConstructor
@Slf4j
public class DefaultConsumeServerConsumer {

  //  private final KafkaConsumerControlService kafkaConsumeService;


    @Value("${spring.kafka.bootstrap-servers}")
    private  String Kafka_Main_Sever;

    //@KafkaListener(topics = "create", groupId = "admin-group",containerFactory ="adminKafkaListenerContainerFactory" )
   /* public void createAdminConsumer(String topicName){
        String [] arr=topicName.split("-");
        log.info("servername:{}",Kafka_Main_Sever);
        log.info("create consumer consume topic:{}",topicName);
        kafkaConsumeService.createConsumer(topicName,Long.parseLong(arr[1]));
    }*/
    //@KafkaListener(topics = "del", groupId = "admin-group",containerFactory = "adminKafkaListenerContainerFactory")
    /*public void delAdminConsumer(String topicName){
        String [] arr=topicName.split("-");
        log.info("delete consumer consume topic:{}",topicName);
        kafkaConsumeService.removeConsumer(topicName,Long.parseLong(arr[1]));
    }*/
}
