package com.planpal.demo.web.dto.user;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

public class UserRequestDto {

    @Getter
    public static class LoginDto {
        @NotNull
        private Long kakaoId;
        private String nickname;
    }
}
