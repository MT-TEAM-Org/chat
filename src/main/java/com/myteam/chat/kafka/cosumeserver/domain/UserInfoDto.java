package com.myteam.chat.kafka.cosumeserver.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
public class UserInfoDto {
    private String token;
    private UUID publicId;
    private String nickname;
    private String profileImage;
    public UserInfo createUserInfo(){
        return new UserInfo(this.publicId,this.nickname,this.profileImage);
    }
}
