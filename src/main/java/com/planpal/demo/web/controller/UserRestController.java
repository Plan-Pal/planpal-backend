package com.planpal.demo.web.controller;

import com.planpal.demo.apipayload.ApiResponse;
import com.planpal.demo.apipayload.status.SuccessStatus;
import com.planpal.demo.converter.UserConverter;
import com.planpal.demo.service.user.SocialLoginService;
import com.planpal.demo.service.user.UserCommandService;
import com.planpal.demo.web.dto.UserResponseDto.SignUpResultDto;
import com.planpal.demo.web.dto.kakao.KakaoUserInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class UserRestController {

    private final SocialLoginService socialLoginService;
    private final UserCommandService userCommandService;

    @PostMapping("/signup")
    public ApiResponse<SignUpResultDto> getLoginWithCode(@RequestBody Map<String, String> code){
        String codeContent=code.get("code");
        KakaoUserInfoDto userInfo=socialLoginService.getUserInfoFromCode(codeContent);
        String accessToken = userCommandService.login(userInfo);
        return ApiResponse.of(SuccessStatus._OK, UserConverter.toSignUpResultDto(accessToken));
    }
}
