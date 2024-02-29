package com.planpal.demo.web.controller.user;

import com.planpal.demo.apipayload.ApiResponse;
import com.planpal.demo.apipayload.status.SuccessStatus;
import com.planpal.demo.service.user.UserCommandService;
import com.planpal.demo.web.dto.user.UserRequestDto.LoginDto;
import com.planpal.demo.web.dto.user.UserResponseDto.LoginResultDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserRestController {

    private final UserCommandService userCommandService;

    @PostMapping("/signup")
    public ApiResponse<LoginResultDto> login(@RequestBody @Valid LoginDto loginDto){
        LoginResultDto loginResultDto = userCommandService.login(loginDto);
        return ApiResponse.of(SuccessStatus._OK, loginResultDto);
    }
}
