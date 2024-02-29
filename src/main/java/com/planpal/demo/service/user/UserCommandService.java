package com.planpal.demo.service.user;

import com.planpal.demo.auth.jwt.JwtUtils;
import com.planpal.demo.converter.UserConverter;
import com.planpal.demo.domain.User;
import com.planpal.demo.domain.UserRefreshToken;
import com.planpal.demo.repository.UserRefreshTokenRepository;
import com.planpal.demo.repository.UserRepository;
import com.planpal.demo.web.dto.user.UserRequestDto.LoginDto;
import com.planpal.demo.web.dto.user.UserResponseDto.LoginResultDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserCommandService {

    private final JwtUtils jwtUtils;
    private final UserRepository userRepository;
    private final UserRefreshTokenRepository userRefreshTokenRepository;

    public LoginResultDto login(LoginDto userInfo) {
        User user = findUser(userInfo);

        String accessToken = jwtUtils.generateAccessToken(user.getId());
        String refreshToken = jwtUtils.generateRefreshToken(user.getId());

        saveRefreshToken(user, refreshToken);

        return UserConverter.toLoginResultDto(accessToken, refreshToken);
    }

    private User findUser(LoginDto userInfo) {
        return userRepository.findByKakaoId(userInfo.getKakaoId())
                .orElseGet(() -> {
                    User newUser = UserConverter.toUser(userInfo);
                    userRepository.save(newUser);
                    return newUser;
                });
    }

    private void saveRefreshToken(User user, String refreshToken) {
        Optional<UserRefreshToken> userRefreshToken = userRefreshTokenRepository.findByUser(user);
        if (userRefreshToken.isPresent()) {
            userRefreshToken.get().update(refreshToken);
        }
        else {
            UserRefreshToken newUserRefreshToken = UserConverter.toUserRefreshToken(user, refreshToken);
            userRefreshTokenRepository.save(newUserRefreshToken);
        }
    }
}
