package com.planpal.demo.web.dto.friend;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

public class FriendRequestDto {
    @Getter
    public static class FriendDto {
        @NotNull
        private Long friendId;
    }

    @Getter
    public static class RequestDto {
        @NotNull
        private Long friendRequestId;
    }
}
