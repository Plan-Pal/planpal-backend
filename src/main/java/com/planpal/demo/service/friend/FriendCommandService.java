package com.planpal.demo.service.friend;

import com.planpal.demo.apipayload.status.ErrorStatus;
import com.planpal.demo.converter.FriendConverter;
import com.planpal.demo.domain.User;
import com.planpal.demo.domain.mapping.FriendRequest;
import com.planpal.demo.exception.ex.FriendException;
import com.planpal.demo.exception.ex.UserException;
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
    private final FriendRequestRepository friendRequestRepository;

    public void sendFriendRequest(Long userId, RequestDto requestDto) {
        Long friendId = requestDto.getFriendId();

        User sender = userRepository.findById(userId)
                .orElseThrow(() -> new UserException(ErrorStatus.USER_NOT_FOUND));

        User receiver = userRepository.findById(friendId)
                .orElseThrow(() -> new FriendException(ErrorStatus.FRIEND_NOT_FOUND));

        validate(sender, receiver);

        FriendRequest friendRequest = FriendConverter.toFriendRequest(sender, receiver);
        friendRequestRepository.save(friendRequest);
    }

    private void validate(User sender, User receiver) {
        validateNewRequest(sender, receiver);
        validateNotMyself(sender, receiver);
        // TODO: 이미 친구로 맺어진 경우 예외 호출
    }

    private void validateNewRequest(User sender, User receiver) {
        boolean alreadyExists = friendRequestRepository.existsBySenderAndReceiver(sender, receiver);
        if (alreadyExists) {
            throw new FriendException(ErrorStatus.REQUEST_ALREADY_EXISTS);
        }
    }
    private static void validateNotMyself(User sender, User receiver) {
        if (sender == receiver) {
            throw new UserException(ErrorStatus.NOT_APPLY_MYSELF);
        }
    }
}
