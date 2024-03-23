package com.planpal.demo.web.dto.user;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

public class UserRequestDto {

    @Getter
    public static class LoginDto {
        @NotNull
        private Long kakaoId;
        @NotNull
        private String nickname;
    }

    @Getter
    public static class JwtRequestDto {
        @NotNull
        private String accessToken;
        @NotNull
        private String refreshToken;
    }

    @Getter
    public static class UpdateDto {
        @NotNull
        private Integer iconId;
        @NotEmpty
        @Size(max = 10)
        private String nickname;
    }
}
