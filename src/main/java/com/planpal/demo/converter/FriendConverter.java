package com.planpal.demo.converter;

import com.planpal.demo.domain.User;
import com.planpal.demo.domain.mapping.Friend;
import com.planpal.demo.domain.mapping.FriendRequest;

public class FriendConverter {

    public static FriendRequest toFriendRequest(User sender, User receiver) {
        return new FriendRequest(sender, receiver);
    }

    public static Friend toFriend(User user1, User user2) {
        return new Friend(user1, user2);
    }
}
