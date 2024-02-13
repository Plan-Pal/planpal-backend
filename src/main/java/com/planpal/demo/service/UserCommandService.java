package com.planpal.demo.service;

import com.planpal.demo.domain.User;
import com.planpal.demo.web.dto.UserRequestDto.JoinDto;

public interface UserCommandService {
    User join(JoinDto joinDto);
}
