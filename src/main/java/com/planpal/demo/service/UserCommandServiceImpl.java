package com.planpal.demo.service;

import com.planpal.demo.converter.UserConverter;
import com.planpal.demo.domain.User;
import com.planpal.demo.repository.UserRepository;
import com.planpal.demo.web.dto.UserRequestDto.JoinDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserCommandServiceImpl implements UserCommandService {

    private final UserRepository userRepository;
    @Override
    public User join(JoinDto joinDto) {
        User user = UserConverter.toUser(joinDto);
        userRepository.save(user);
        return user;
    }
}
