package com.myteam.chat.kafka.cosumeserver.redis.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RedisChatRoomService {

    private final RedisTemplate<String, Object> redisTemplate;
    private static final String PREFIX = "roomCheck";


    public void closeRoom(Long roomId){
        redisTemplate.opsForSet().remove(PREFIX,String.valueOf(roomId));
    }
    public void openRoom(Long roomId){
        redisTemplate.opsForSet().add(PREFIX,String.valueOf(roomId));
    }
    public Boolean checkRoom(Long roomId){
       return redisTemplate.opsForSet().isMember(PREFIX,String.valueOf(roomId));
    }

}
