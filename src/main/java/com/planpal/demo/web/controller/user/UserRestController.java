package com.planpal.demo.web.controller.user;

import com.planpal.demo.apipayload.ApiResponse;
import com.planpal.demo.service.user.UserCommandService;
import com.planpal.demo.web.dto.user.UserRequestDto.JwtRequestDto;
import com.planpal.demo.web.dto.user.UserRequestDto.LoginDto;
import com.planpal.demo.web.dto.user.UserRequestDto.UpdateDto;
import com.planpal.demo.web.dto.user.UserResponseDto.JwtResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserRestController {

    private final UserCommandService userCommandService;

    @PostMapping("/signup")
    public ApiResponse<JwtResponseDto> login(@RequestBody @Valid LoginDto loginDto){
        JwtResponseDto jwtResponseDto = userCommandService.login(loginDto);
        return ApiResponse.onSuccess(jwtResponseDto);
    }

    @PostMapping("/refresh")
    public ApiResponse<JwtResponseDto> refresh(@RequestBody @Valid JwtRequestDto jwtRequestDto) {
        JwtResponseDto jwtResponseDto = userCommandService.refresh(jwtRequestDto);
        return ApiResponse.onSuccess(jwtResponseDto);
    }

    @PatchMapping("/users")
    public ApiResponse<Void> update(@AuthenticationPrincipal Long userId,
                                    @RequestBody @Valid UpdateDto updateDto) {
        userCommandService.update(userId, updateDto);
        return ApiResponse.onSuccess(null);
    }
}
