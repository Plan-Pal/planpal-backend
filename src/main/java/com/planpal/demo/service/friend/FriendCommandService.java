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

    public void sendFriendRequest(Long userId, RequestDto requestDto) {
        User sender = userRepository.findById(userId)
                .orElseThrow(() -> new UserException(ErrorStatus.USER_NOT_FOUND));

        User receiver = userRepository.findById(requestDto.getFriendId())
                .orElseThrow(() -> new FriendException(ErrorStatus.FRIEND_NOT_FOUND));

        validateCanSend(sender, receiver);

        FriendRequest friendRequest = FriendConverter.toFriendRequest(sender, receiver);
        friendRequestRepository.save(friendRequest);
    }

    public void deleteFriendRequest(Long userId, RequestDto requestDto) {
        User sender = userRepository.findById(userId)
                .orElseThrow(() -> new UserException(ErrorStatus.USER_NOT_FOUND));

        User receiver = userRepository.findById(requestDto.getFriendId())
                .orElseThrow(() -> new FriendException(ErrorStatus.FRIEND_NOT_FOUND));

        FriendRequest friendRequest = friendRequestRepository.findBySenderAndReceiver(sender, receiver)
                .orElseThrow(() -> new FriendException(ErrorStatus.FRIEND_REQUEST_NOT_FOUND));
        friendRequestRepository.delete(friendRequest);
    }

    public void acceptFriendRequest(Long userId, RequestDto requestDto) {
        User receiver = userRepository.findById(userId)
                .orElseThrow(() -> new UserException(ErrorStatus.USER_NOT_FOUND));

        User sender = userRepository.findById(requestDto.getFriendId())
                .orElseThrow(() -> new FriendException(ErrorStatus.FRIEND_NOT_FOUND));

        FriendRequest friendRequest = friendRequestRepository.findBySenderAndReceiver(sender, receiver)
                .orElseThrow(() -> new FriendException(ErrorStatus.FRIEND_REQUEST_NOT_FOUND));
        friendRequestRepository.delete(friendRequest);

        Friend friend = FriendConverter.toFriend(sender, receiver);
        friendRepository.save(friend);
    }

    private void validateCanSend(User sender, User receiver) {
        validateNewRequest(sender, receiver);
        validateNotMyself(sender, receiver);
        // TODO: 이미 친구로 맺어진 경우 예외 호출
    }

    private void validateNewRequest(User sender, User receiver) {
        boolean alreadyExists = friendRequestRepository.existsBySenderAndReceiver(sender, receiver);
        if (alreadyExists) {
            throw new FriendException(ErrorStatus.FRIEND_REQUEST_ALREADY_EXISTS);
        }
    }
    private static void validateNotMyself(User sender, User receiver) {
        if (sender == receiver) {
            throw new UserException(ErrorStatus.NOT_APPLY_MYSELF);
        }
    }
}
