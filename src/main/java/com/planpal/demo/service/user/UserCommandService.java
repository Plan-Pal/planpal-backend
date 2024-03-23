package com.planpal.demo.service.user;

import com.planpal.demo.apipayload.status.ErrorStatus;
import com.planpal.demo.auth.jwt.JwtUtils;
import com.planpal.demo.converter.UserConverter;
import com.planpal.demo.domain.User;
import com.planpal.demo.domain.UserRefreshToken;
import com.planpal.demo.exception.ex.UserException;
import com.planpal.demo.repository.UserRefreshTokenRepository;
import com.planpal.demo.repository.UserRepository;
import com.planpal.demo.web.dto.user.UserRequestDto.JwtRequestDto;
import com.planpal.demo.web.dto.user.UserRequestDto.LoginDto;
import com.planpal.demo.web.dto.user.UserRequestDto.UpdateDto;
import com.planpal.demo.web.dto.user.UserResponseDto.JwtResponseDto;
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

    public JwtResponseDto login(LoginDto userInfo) {
        User user = getOrCreateUser(userInfo);

        JwtResponseDto newTokenDto = createNewToken(user.getId());
        saveRefreshToken(user, newTokenDto.getRefreshToken());

        return newTokenDto;
    }

    public JwtResponseDto refresh(JwtRequestDto jwtRequestDto) {
        User user = getUser(jwtRequestDto.getAccessToken());
        UserRefreshToken userRefreshToken = getUserRefreshToken(jwtRequestDto.getRefreshToken(), user);

        JwtResponseDto newTokenDto = createNewToken(user.getId());
        userRefreshToken.update(newTokenDto.getRefreshToken());

        return newTokenDto;
    }

    public void update(Long userId, UpdateDto updateDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserException(ErrorStatus.USER_NOT_FOUND));
        user.update(updateDto);
    }

    private User getOrCreateUser(LoginDto userInfo) {
        return userRepository.findByKakaoId(userInfo.getKakaoId())
                .orElseGet(() -> {
                    User newUser = UserConverter.toUser(userInfo);
                    userRepository.save(newUser);
                    return newUser;
                });
    }

    private JwtResponseDto createNewToken(Long userId) {
        String accessToken = jwtUtils.generateAccessToken(userId);
        String refreshToken = jwtUtils.generateRefreshToken();

        return UserConverter.toJwtResponseDto(accessToken, refreshToken);
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

    private User getUser(String accessToken) {
        return userRepository.findById((Long) jwtUtils.getAuthentication(accessToken).getPrincipal())
                .orElseThrow(() -> new UserException(ErrorStatus.TOKEN_INVALID));
    }

    private UserRefreshToken getUserRefreshToken(String refreshToken, User user) {
        UserRefreshToken userRefreshToken = userRefreshTokenRepository.findByUser(user)
                .orElseThrow(() -> new UserException(ErrorStatus.TOKEN_INVALID));

        if (!userRefreshToken.getRefreshToken().equals(refreshToken)) {
            throw new UserException(ErrorStatus.TOKEN_INVALID);
        }

        try {
            jwtUtils.validate(refreshToken);
        } catch (Exception e) {
            throw new UserException(ErrorStatus.TOKEN_INVALID);
        }

        return userRefreshToken;
    }
}
