package com.planpal.demo.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class UserResponseDto {

    @Getter
    @AllArgsConstructor
    public static class SignUpResultDto {
        private String accessToken;
    }
}
