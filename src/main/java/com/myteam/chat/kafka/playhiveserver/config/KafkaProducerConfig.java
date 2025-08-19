package com.myteam.chat.kafka.playhiveserver.config;


import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class KafkaProducerConfig {

    private final String Kafka_Main_Sever="localhost:9092";

    //메인 서버의 사용자에대한 유저정보 업데이트 시(userinfo) 각 채팅서버의 redis에 저장된
    //user info의 업데이트를 위함.
    @Bean
    public ProducerFactory<String,String> KafkaUserInfoProducer(){
        Map<String,Object> config=new HashMap<>();
        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,Kafka_Main_Sever);
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,StringSerializer.class);
        return new DefaultKafkaProducerFactory<>(config);
    }

    @Bean
    public KafkaTemplate<String, String> kafkaUpdateUserTemplate(){
        return new KafkaTemplate<>(KafkaUserInfoProducer());
    }

}
