package com.planpal.demo.web.dto.friend;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class FriendResponseDto {
    @Getter
    @AllArgsConstructor
    public static class GetSentFriendRequestDto {
        private Long friendRequestId;
        private String receiverNickname;
        private String receiverTagId;
    }

    @Getter
    @AllArgsConstructor
    public static class GetReceivedFriendRequestDto {
        private Long friendRequestId;
        private String senderNickname;
        private String senderTagId;
    }
}
