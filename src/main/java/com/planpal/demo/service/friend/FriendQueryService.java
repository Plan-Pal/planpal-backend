package com.planpal.demo.service.friend;

import com.planpal.demo.apipayload.status.ErrorStatus;
import com.planpal.demo.domain.User;
import com.planpal.demo.domain.mapping.FriendRequest;
import com.planpal.demo.exception.ex.UserException;
import com.planpal.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FriendQueryService {

    private final UserRepository userRepository;

    public List<User> getUsersByNickname(String nickname) {
        return userRepository.findByNicknameContaining(nickname);
    }

    public List<User> getUsersByTagId(String tagId) {
        return userRepository.findByTagIdStartsWith(tagId);
    }

    public List<User> getFriendRequestSenders(Long userId) {
        User invitee = userRepository.findById(userId)
                .orElseThrow(() -> new UserException(ErrorStatus.USER_NOT_FOUND));
        return invitee.getInvitedList().stream()
                .map(FriendRequest::getInviter)
                .toList();
    }
}
