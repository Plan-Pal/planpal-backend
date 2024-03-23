package com.planpal.demo.service.friend;

import com.planpal.demo.apipayload.status.ErrorStatus;
import com.planpal.demo.converter.FriendConverter;
import com.planpal.demo.domain.User;
import com.planpal.demo.domain.mapping.Friend;
import com.planpal.demo.domain.mapping.FriendRequest;
import com.planpal.demo.exception.ex.FriendException;
import com.planpal.demo.exception.ex.UserException;
import com.planpal.demo.repository.FriendRepository;
import com.planpal.demo.repository.FriendRequestRepository;
import com.planpal.demo.repository.UserRepository;
import com.planpal.demo.web.dto.friend.FriendRequestDto.FriendDto;
import com.planpal.demo.web.dto.friend.FriendRequestDto.RequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class FriendCommandService {

    private final UserRepository userRepository;
    private final FriendRepository friendRepository;
    private final FriendRequestRepository friendRequestRepository;

    public void sendFriendRequest(Long userId, FriendDto friendDto) {
        User sender = userRepository.findById(userId)
                .orElseThrow(() -> new UserException(ErrorStatus.USER_NOT_FOUND));

        User receiver = userRepository.findById(friendDto.getUserId())
                .orElseThrow(() -> new FriendException(ErrorStatus.USER_NOT_FOUND));

        validateCanSend(sender, receiver);

        FriendRequest friendRequest = FriendConverter.toFriendRequest(sender, receiver);
        friendRequestRepository.save(friendRequest);
    }

    public void deleteFriendRequest(Long userId, RequestDto requestDto) {
        User sender = userRepository.findById(userId)
                .orElseThrow(() -> new UserException(ErrorStatus.USER_NOT_FOUND));

        FriendRequest friendRequest = friendRequestRepository.findByIdAndSender(requestDto.getFriendRequestId(), sender)
                .orElseThrow(() -> new FriendException(ErrorStatus.FRIEND_REQUEST_NOT_FOUND));

        friendRequestRepository.delete(friendRequest);
    }

    public User acceptFriendRequest(Long userId, RequestDto requestDto) {
        User receiver = userRepository.findById(userId)
                .orElseThrow(() -> new UserException(ErrorStatus.USER_NOT_FOUND));

        FriendRequest friendRequest = friendRequestRepository.findByIdAndReceiver(requestDto.getFriendRequestId(), receiver)
                .orElseThrow(() -> new FriendException(ErrorStatus.FRIEND_REQUEST_NOT_FOUND));

        Friend friend = FriendConverter.toFriend(friendRequest.getSender(), receiver);

        friendRequestRepository.delete(friendRequest);
        friendRepository.save(friend);

        return friendRequest.getSender();
    }

    public void deleteFriend(Long userId, FriendDto friendDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserException(ErrorStatus.USER_NOT_FOUND));

        User friendUser = userRepository.findById(friendDto.getUserId())
                .orElseThrow(() -> new UserException(ErrorStatus.USER_NOT_FOUND));

        Friend friend = friendRepository.findByUser1AndUser2(user, friendUser)
                .orElseGet(() -> friendRepository.findByUser1AndUser2(friendUser, user)
                        .orElseThrow(() -> new FriendException(ErrorStatus.FRIEND_NOT_FOUND)));

        friendRepository.delete(friend);
    }

    private void validateCanSend(User sender, User receiver) {
        validateNewRequest(sender, receiver);
        validateNotMyself(sender, receiver);
        validateNotFriend(sender, receiver);
    }

    private void validateNewRequest(User sender, User receiver) {
        if (friendRequestRepository.existsBySenderAndReceiver(sender, receiver)) {
            throw new FriendException(ErrorStatus.FRIEND_REQUEST_ALREADY_EXISTS);
        }
        if (friendRequestRepository.existsBySenderAndReceiver(receiver, sender)) {
            throw new FriendException(ErrorStatus.RECEIVED_FRIEND_REQUEST_ALREADY_EXISTS);
        }
    }

    private static void validateNotMyself(User sender, User receiver) {
        if (sender == receiver) {
            throw new UserException(ErrorStatus.NOT_APPLY_MYSELF);
        }
    }

    private void validateNotFriend(User user1, User user2) {
        boolean alreadyExists = friendRepository.existsByUser1AndUser2(user1, user2)
                || friendRepository.existsByUser1AndUser2(user2, user1);
        if (alreadyExists) {
            throw new FriendException(ErrorStatus.FRIEND_ALREADY_EXISTS);
        }
    }
}
