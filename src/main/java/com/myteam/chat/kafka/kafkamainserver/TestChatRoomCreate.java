package com.myteam.chat.kafka.kafkamainserver;

import com.myteam.chat.kafka.cosumeserver.redis.service.RedisChatRoomService;
import com.myteam.chat.kafka.kafkamainserver.domain.ChatRoom;
import com.myteam.chat.kafka.kafkamainserver.event.ConsumerCreateEvent;
import com.myteam.chat.kafka.kafkamainserver.event.ConsumerDelEvent;
import com.myteam.chat.kafka.kafkamainserver.match.match.domain.Match;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.concurrent.ExecutionException;

//@RestController
@RequiredArgsConstructor
@Slf4j
@Transactional
public class TestChatRoomCreate {

    private final RedisChatRoomService redisChatRoomService;
    private final KafkaAdmin kafkaAdmin;
    private final ApplicationEventPublisher publisher;
    private final KafkaTemplate<String,String> adminKafkaTemplate;
    private static final String TOPIC_PREFIX = "topic_chat.match-";
    @GetMapping("/test")
    public String createSetting(){

            try (AdminClient adminClient = AdminClient.create(kafkaAdmin.getConfigurationProperties())) {
                //redisChatRoomService.openRoom(1L);
                NewTopic topic = TopicBuilder
                        .name(TOPIC_PREFIX+1L)
                        .partitions(3)
                        .build();
                adminClient.createTopics(Collections.singletonList(topic)).all().get();
                log.info("Topic '{}' created successfully with {} partitions and replication factor {}.",
                        topic.name(),topic.numPartitions(),1);
               ConsumerCreateEvent consumerControlEvent=ConsumerCreateEvent
                        .builder()
                        .topic(TOPIC_PREFIX+1L)
                        .build();
                adminKafkaTemplate.send("create",TOPIC_PREFIX+1L);
                return "ok";
            } catch (ExecutionException | InterruptedException e) {
                log.error("Failed to create topic about match {}", e.getMessage());
                Thread.currentThread().interrupt(); // 인터럽트 복원
                /*if(chatRoom!=null) {
                    chatRoomService.eraseChatRoom(chatRoom.getId());
                }*/
            }
            return "ok";
    }
    @GetMapping("/del")
    public String deltest(){

        try (AdminClient adminClient =
                     AdminClient.create(kafkaAdmin.getConfigurationProperties())) {
            adminClient.deleteTopics(Collections.singletonList(TOPIC_PREFIX+1L)).all().get();
            adminKafkaTemplate.send("del",TOPIC_PREFIX+1L);
            log.info("delete scuccess");
        } catch (Exception e) {
            log.error("Failed to delete topic {}",e.getMessage());
            Thread.currentThread().interrupt(); // 인터럽트 복원
        }
        return "ok";
    }
}
