package com.planpal.demo.web.dto;

import com.planpal.demo.domain.enums.SocialType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

public class UserRequestDto {

    @Getter
    public static class JoinDto {
        @NotBlank
        @Length(max = 10)
        private String nickname;
        @Email
        @NotBlank
        @Length(max = 70)
        private String email;
        @NotNull
        private SocialType socialType;
    }
}
