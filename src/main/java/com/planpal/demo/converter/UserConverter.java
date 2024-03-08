package com.planpal.demo.converter;

import com.planpal.demo.domain.User;
import com.planpal.demo.domain.UserRefreshToken;
import com.planpal.demo.web.dto.schedule.SimpleUserInfo;
import com.planpal.demo.web.dto.user.UserRequestDto.LoginDto;
import com.planpal.demo.web.dto.user.UserResponseDto.GetProfileDto;
import com.planpal.demo.web.dto.user.UserResponseDto.GetResultDto;
import com.planpal.demo.web.dto.user.UserResponseDto.JwtResponseDto;

public class UserConverter {
    public static User toUser(LoginDto userInfoDto) {
        return User.builder()
                .kakaoId(userInfoDto.getKakaoId())
                .nickname(userInfoDto.getNickname())
                .build();
    }

    public static JwtResponseDto toJwtResponseDto(String accessToken, String refreshToken) {
        return new JwtResponseDto(accessToken, refreshToken);
    }

    public static GetResultDto toGetResultDto(User user) {
        return new GetResultDto(
                user.getId(),
                user.getNickname(),
                user.getTagId(),
                user.getIconId());
    }

    public static SimpleUserInfo toSimpleUserInfo(User user){
        return new SimpleUserInfo(user.getNickname());
    }

    public static UserRefreshToken toUserRefreshToken(User user, String refreshToken) {
        return UserRefreshToken.builder()
                .user(user)
                .refreshToken(refreshToken)
                .build();
    }

    public static GetProfileDto toGetProfileDto(User user) {
        return new GetProfileDto(user.getNickname(), user.getTagId(), user.getIconId());
    }
}
