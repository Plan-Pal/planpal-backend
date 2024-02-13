package com.planpal.demo.converter;

import com.planpal.demo.domain.User;
import com.planpal.demo.web.dto.UserRequestDto.JoinDto;

public class UserConverter {
    public static User toUser(JoinDto joinDto) {
        return User.builder()
                .email(joinDto.getEmail())
                .nickname(joinDto.getNickname())
                .socialType(joinDto.getSocialType())
                .build();
    }
}
