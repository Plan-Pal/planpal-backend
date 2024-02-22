package com.planpal.demo.converter;

import com.planpal.demo.domain.User;
import com.planpal.demo.domain.mapping.FriendRequest;
import com.planpal.demo.web.dto.friend.FriendResponseDto.GetResultDto;

public class FriendConverter {
    public static GetResultDto toGetResultDto(User user) {
        return new GetResultDto(
                user.getId(),
                user.getNickname(),
                user.getTagId());
    }

    public static FriendRequest toFriendRequest(User sender, User receiver) {
        return new FriendRequest(sender, receiver);
    }
}
