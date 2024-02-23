package com.planpal.demo.web.dto.friend;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class FriendResponseDto {

    @Getter
    @AllArgsConstructor
    public static class GetResultDto {
        private Long id;
        private String nickname;
        private String tagId;
    }



}
