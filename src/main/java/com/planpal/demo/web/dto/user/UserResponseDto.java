package com.planpal.demo.web.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class UserResponseDto {

    @Getter
    @AllArgsConstructor
    public static class JwtResponseDto {
        private String accessToken;
        private String refreshToken;
    }

    @Getter
    @AllArgsConstructor
    public static class GetResultDto {
        private Long userId;
        private String nickname;
        private String tagId;
    }
}
