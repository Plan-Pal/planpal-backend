package com.planpal.demo.converter;

import com.planpal.demo.domain.User;
import com.planpal.demo.domain.mapping.Friend;
import com.planpal.demo.domain.mapping.FriendRequest;
import com.planpal.demo.web.dto.friend.FriendResponseDto.GetReceivedFriendRequestDto;
import com.planpal.demo.web.dto.friend.FriendResponseDto.GetSentFriendRequestDto;

public class FriendConverter {

    public static FriendRequest toFriendRequest(User sender, User receiver) {
        return new FriendRequest(sender, receiver);
    }

    public static Friend toFriend(User user1, User user2) {
        return new Friend(user1, user2);
    }

    public static GetSentFriendRequestDto toGetSentFriendRequestDto(FriendRequest request) {
        return new GetSentFriendRequestDto(
                request.getId(),
                request.getReceiver().getNickname(),
                request.getReceiver().getTagId()
        );
    }

    public static GetReceivedFriendRequestDto toGetReceivedFriendRequestDto(FriendRequest request) {
        return new GetReceivedFriendRequestDto(
                request.getId(),
                request.getSender().getNickname(),
                request.getSender().getTagId()
        );
    }
}
