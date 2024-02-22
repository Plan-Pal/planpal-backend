package com.planpal.demo.web.dto.friend;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

public class FriendRequestDto {
    @Getter
    public static class InviteDto {
        @NotNull
        private Long friendId;
    }
}
