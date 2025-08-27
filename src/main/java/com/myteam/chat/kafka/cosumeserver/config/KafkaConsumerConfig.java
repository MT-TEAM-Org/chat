package com.myteam.chat.kafka.cosumeserver.config;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.util.backoff.FixedBackOff;
import java.util.HashMap;
import java.util.Map;

//@Configuration
@RequiredArgsConstructor
@Slf4j
public class KafkaConsumerConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String Kafka_Main_Sever;

    //chatresponse 파싱하는애
    //@Bean
    public ConsumerFactory<String, String> kafkaConsumer(){
        Map<String,Object> config=new HashMap<>();
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,Kafka_Main_Sever);
        config.put(ConsumerConfig.GROUP_ID_CONFIG, "test_group");
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        /*config.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
        JSON으로ㅓ 파싱시에 위의 TRUSTED_PACKAGES는 필수이다.*/
        config.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,"latest");
        return new DefaultKafkaConsumerFactory<>(config);
    }

    //string타입으로 토픽이름 받아서 해당 토픽에 해당되는 consumer의 생성 및 삭제 진행
    //@Bean
    public ConsumerFactory<String,String> KafkaControlConsumer(){
        Map<String,Object> config=new HashMap<>();
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,Kafka_Main_Sever);
        config.put(ConsumerConfig.GROUP_ID_CONFIG, "admin_group");
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,StringDeserializer.class);
        /*config.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
        JSON으로ㅓ 파싱시에 위의 TRUSTED_PACKAGES는 필수이다.*/
        config.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,"latest");
        return new DefaultKafkaConsumerFactory<>(config);
    }



    @Bean
    public ConcurrentKafkaListenerContainerFactory<String,String>
    kafkaListenerContainerFactory(){
        ConcurrentKafkaListenerContainerFactory<String,String> fac
                =new ConcurrentKafkaListenerContainerFactory<>();
        fac.setConsumerFactory(kafkaConsumer());
        //애는 일종의 컨슈머 갯수를 의미함. 즉 kafakalistner는 1개의 컨슈머 그룹에 해당되고
        //이값을 통해서 해당 컨슈머 그룹에 들어갈 컨슈머(스레드)갯수를 정하는것.
        fac.setConcurrency(1);
        DefaultErrorHandler defaultErrorHandler=new DefaultErrorHandler(
                ((consumerRecord, e) -> {
                    log.info("에러 발생으로 메시지 전달 실패:{}",e.getCause().getMessage());
                })
                ,new FixedBackOff(0,0)
        );

        fac.setCommonErrorHandler(defaultErrorHandler);
        //kafkalistner가 아니라 인위적으로 만든 컨슈머의 경우 에러 핸들링을 해당 핸들러에서 진행한다.
        //애초에 프로세스 자체가 controller adivce를 벗어나기에 따로 이렇게 처리해야된다.
        return fac;

    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String,String>
    adminKafkaListenerContainerFactory(){
        ConcurrentKafkaListenerContainerFactory<String,String> fac
                =new ConcurrentKafkaListenerContainerFactory<>();
        fac.setConsumerFactory(KafkaControlConsumer());
        fac.setConcurrency(1);
        return fac;
    }




}
