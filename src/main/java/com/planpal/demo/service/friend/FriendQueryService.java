package com.planpal.demo.service.friend;

import com.planpal.demo.apipayload.status.ErrorStatus;
import com.planpal.demo.domain.User;
import com.planpal.demo.domain.mapping.Friend;
import com.planpal.demo.domain.mapping.FriendRequest;
import com.planpal.demo.exception.ex.UserException;
import com.planpal.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FriendQueryService {

    private final UserRepository userRepository;

    public List<FriendRequest> getSentFriendRequests(Long userId) {
        User sender = userRepository.findById(userId)
                .orElseThrow(() -> new UserException(ErrorStatus.USER_NOT_FOUND));
        return sender.getSentRequests();
    }

    public List<FriendRequest> getReceivedFriendRequests(Long userId) {
        User receiver = userRepository.findById(userId)
                .orElseThrow(() -> new UserException(ErrorStatus.USER_NOT_FOUND));
        return receiver.getReceivedRequests();
    }

    public List<User> getFriends(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserException(ErrorStatus.USER_NOT_FOUND));

        return findFriends(user);
    }

    private static List<User> findFriends(User user) {
        List<User> friends = new ArrayList<>();

        List<User> friendsAsUser1 = user.getFriendsAsUser1().stream()
                .map(Friend::getUser2)
                .toList();

        List<User> friendsAsUser2 = user.getFriendsAsUser2().stream()
                .map(Friend::getUser1)
                .toList();

        friends.addAll(friendsAsUser1);
        friends.addAll(friendsAsUser2);

        return friends;
    }
}
