package com.planpal.demo.service.user;

import com.planpal.demo.auth.jwt.JwtUtils;
import com.planpal.demo.converter.UserConverter;
import com.planpal.demo.domain.User;
import com.planpal.demo.repository.UserRepository;
import com.planpal.demo.web.dto.kakao.KakaoUserInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserCommandService {

    private final JwtUtils jwtUtils;
    private final UserRepository userRepository;

    public String login(KakaoUserInfoDto userInfo) {
        User user = findUser(userInfo);
        return jwtUtils.generateAccessToken(user.getId());
    }

    private User findUser(KakaoUserInfoDto userInfo) {
        return userRepository.findByKakaoId(userInfo.getId())
                .orElseGet(() -> {
                    User newUser = UserConverter.toUser(userInfo);
                    userRepository.save(newUser);
                    return newUser;
                });
    }
}
