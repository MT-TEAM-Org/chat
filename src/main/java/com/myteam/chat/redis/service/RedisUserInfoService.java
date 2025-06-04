package com.myteam.chat.redis.service;

import com.myteam.chat.chat.domain.UserInfo;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
        try {
            String value = (String) redisTemplate.opsForValue().get(key);
            if (value == null) return Optional.empty();
            return Optional.of(objectMapper.readValue(value, UserInfo.class));
        } catch (Exception e) {
            log.error("Failed to deserialize userInfo", e);
            return Optional.empty();
        }
    }
}
