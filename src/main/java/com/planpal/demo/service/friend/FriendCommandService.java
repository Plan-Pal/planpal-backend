package com.planpal.demo.service.friend;

import com.planpal.demo.apipayload.status.ErrorStatus;
import com.planpal.demo.converter.FriendConverter;
import com.planpal.demo.domain.User;
import com.planpal.demo.domain.mapping.FriendRequest;
import com.planpal.demo.exception.ex.FriendException;
import com.planpal.demo.exception.ex.UserException;
import com.planpal.demo.repository.FriendRequestRepository;
import com.planpal.demo.repository.UserRepository;
import com.planpal.demo.web.dto.friend.FriendRequestDto.InviteDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class FriendCommandService {

    private final UserRepository userRepository;
    private final FriendRequestRepository friendRequestRepository;

    public void inviteFriend(Long userId, InviteDto inviteDto) {
        Long friendId = inviteDto.getFriendId();

        User inviter = userRepository.findById(userId)
                .orElseThrow(() -> new UserException(ErrorStatus.USER_NOT_FOUND));

        User invitee = userRepository.findById(friendId)
                .orElseThrow(() -> new FriendException(ErrorStatus.FRIEND_NOT_FOUND));

        validate(inviter, invitee);

        FriendRequest friendRequest = FriendConverter.toFriendRequest(inviter, invitee);
        friendRequestRepository.save(friendRequest);
    }

    private void validate(User inviter, User invitee) {
        validateNewRequest(inviter, invitee);
        validateNotMyself(inviter, invitee);
        // TODO: 이미 친구로 맺어진 경우 예외 호출
    }

    private void validateNewRequest(User inviter, User invitee) {
        boolean alreadyExists = friendRequestRepository.existsByInviterAndInvitee(inviter, invitee);
        if (alreadyExists) {
            throw new FriendException(ErrorStatus.REQUEST_ALREADY_EXISTS);
        }
    }
    private static void validateNotMyself(User inviter, User invitee) {
        if (inviter == invitee) {
            throw new UserException(ErrorStatus.NOT_APPLY_MYSELF);
        }
    }
}
