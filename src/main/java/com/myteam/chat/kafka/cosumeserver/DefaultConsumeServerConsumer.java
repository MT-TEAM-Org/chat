package com.myteam.chat.kafka.cosumeserver;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.myteam.chat.kafka.cosumeserver.domain.UserInfoDto;
import com.myteam.chat.kafka.cosumeserver.service.KafkaConsumerControlService;
import com.myteam.chat.kafka.cosumeserver.redis.service.RedisChatRoomService;
import com.myteam.chat.kafka.cosumeserver.redis.service.RedisUserInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class DefaultConsumeServerConsumer {

    private final KafkaConsumerControlService kafkaConsumeService;
    private final RedisUserInfoService redisUserInfoService;
    private final ObjectMapper objectMapper;
    @KafkaListener(topics = "create", groupId = "admin-group",containerFactory ="adminKafkaListenerContainerFactory" )
    public void createAdminConsumer(String topicName){
        String [] arr=topicName.split("-");
        kafkaConsumeService.createConsumer(topicName,Long.parseLong(arr[1]));
    }
    @KafkaListener(topics = "del", groupId = "admin-group",containerFactory = "adminKafkaListenerContainerFactory")
    public void delAdminConsumer(String topicName){
        String [] arr=topicName.split("-");
        kafkaConsumeService.removeConsumer(topicName,Long.parseLong(arr[1]));

    }
    @KafkaListener(topics = "user-del", groupId = "admin-group",containerFactory = "kafkaUserUpdateListenerContainerFactory")
    public void delUserInfoConsumer(String userInfoDto){
        try {
            UserInfoDto userInfoDto1 = objectMapper.readValue(userInfoDto, UserInfoDto.class);
            redisUserInfoService.delUserInfo(userInfoDto1.getToken());
        }
        catch (Exception e){
            log.error("회원 정보 삭제중 에러발생:{}",e.getMessage());
        }
    }
    @KafkaListener(topics = "user-update", groupId = "admin-group",containerFactory = "kafkaUserUpdateListenerContainerFactory")
    public void updateUserInfoConsumer(String userInfoDto){
        try {
            UserInfoDto userInfoDto1 = objectMapper.readValue(userInfoDto, UserInfoDto.class);
            redisUserInfoService.updateUserInfo(userInfoDto1.getToken(), userInfoDto1.
                    createUserInfo());
        }
        catch (Exception e){
            log.error("회원 정보 갱신중 에러발생:{}",e.getMessage());
        }
    }
}
