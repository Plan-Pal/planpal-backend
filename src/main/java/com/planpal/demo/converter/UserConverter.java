package com.planpal.demo.converter;

import com.planpal.demo.domain.User;
import com.planpal.demo.web.dto.UserResponseDto.GetResultDto;
import com.planpal.demo.web.dto.UserResponseDto.SignUpResultDto;
import com.planpal.demo.web.dto.kakao.KakaoUserInfoDto;

public class UserConverter {
    public static User toUser(KakaoUserInfoDto userInfoDto) {
        return User.builder()
                .kakaoId(userInfoDto.getId())
                .nickname(userInfoDto.getNickname())
                .build();
    }

    public static SignUpResultDto toSignUpResultDto(String accessToken) {
        return new SignUpResultDto(accessToken);
    }

    public static GetResultDto toGetResultDto(User user) {
        return new GetResultDto(
                user.getId(),
                user.getNickname(),
                user.getTagId());
    }
}
