package com.myteam.chat.kafka.cosumeserver.redis.service;

import com.myteam.chat.kafka.cosumeserver.domain.UserInfo;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@RequiredArgsConstructor
public class RedisUserInfoService {

    private final RedisTemplate<String, Object> redisTemplate;
    private static final String PREFIX = "token:";
    private final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 채팅서버에서 사용
     * @param token
     */
    public Optional<UserInfo> getUserInfo(String token) {
        String key = PREFIX + token;
        log.info("redis user info get key: {}", key);
        try {
            String value = (String) redisTemplate.opsForValue().get(key);
            if (value == null) return Optional.empty();
            return Optional.of(objectMapper.readValue(value, UserInfo.class));
        } catch (Exception e) {
            log.error("Failed to deserialize userInfo", e);
            return Optional.empty();
        }
    }

    public void delUserInfo(String token){
        String key = PREFIX + token;
        redisTemplate.delete(key);
    }
    public void updateUserInfo(String token,UserInfo userInfo){
        try {
            String key = PREFIX + token;
            String value = objectMapper.writeValueAsString(userInfo);
            redisTemplate.opsForValue().set(key, userInfo, 1L, TimeUnit.DAYS);
        }
        catch (Exception e){
            log.error("fail to update user info",e);
        }
    }
}
