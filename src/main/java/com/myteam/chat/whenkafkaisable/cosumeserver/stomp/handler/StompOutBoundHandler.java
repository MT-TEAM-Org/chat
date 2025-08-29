package com.myteam.chat.whenkafkaisable.cosumeserver.stomp.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.myteam.chat.currentChatDirectory.domain.UserInfo;
import com.myteam.chat.currentChatDirectory.redis.service.RedisUserInfoService;
import com.myteam.chat.currentChatDirectory.repository.BlockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.*;


/*@Component
@RequiredArgsConstructor
public class StompOutBoundHandler implements ChannelInterceptor {

    private final ObjectMapper objectMapper;
    private final RedisUserInfoService redisUserInfoService;
    private final BlockRepository blockRepository;

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        GenericMessage genericMessage=(GenericMessage) message.getHeaders().get("simpConnectMessage");
        StompHeaderAccessor accessor=StompHeaderAccessor.wrap(message);

        if(genericMessage!=null) {
            MessageHeaders messageHeaders=genericMessage.getHeaders();
            Map<String,Object> maps=(Map)messageHeaders.get("nativeHeaders");
            Map<String,Object> mapsBySessionAttrs=(Map)messageHeaders.get("simpSessionAttributes");
            StompHeaderAccessor newAccessor = StompHeaderAccessor.create(StompCommand.CONNECTED);
            newAccessor.copyHeaders(accessor.toMap());
            maps.entrySet().stream().forEach(x->{
                List<String> list=(List<String>) x.getValue();
                if(x.getKey().equals("heart-beat")){
                    newAccessor.setNativeHeader(x.getKey(),String.join(",",List.of("0","0")));
                }
                else {
                    newAccessor.setNativeHeader(x.getKey(), String.join(",", list));
                }
            });
            try {
                String token=(String) mapsBySessionAttrs.get("token");
                Optional<UserInfo> userInfo=redisUserInfoService.getUserInfo(token);
                if(userInfo.isPresent()) {
                    List<UUID> blockList=blockRepository.getByUserId(userInfo.get().getPublicId());
                    byte[] data = objectMapper.writeValueAsBytes(blockList);
                    return MessageBuilder.createMessage(data, newAccessor.getMessageHeaders());
                }
            }
            catch (Exception e){
                //USERNAME만 작성된다.
                return ChannelInterceptor.super.preSend(message, channel);
            }
        }

        return ChannelInterceptor.super.preSend(message, channel);
    }
}*/
