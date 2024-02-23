package com.planpal.demo.service.friend;

import com.planpal.demo.domain.User;
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
}
