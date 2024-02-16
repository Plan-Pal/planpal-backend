package com.planpal.demo.web.controller;

import com.planpal.demo.apipayload.ApiResponse;
import com.planpal.demo.apipayload.status.SuccessStatus;
import com.planpal.demo.domain.User;
import com.planpal.demo.service.UserCommandService;
import com.planpal.demo.service.user.SocialLoginService;
import com.planpal.demo.web.dto.UserRequestDto.JoinDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class UserRestController {

    private final UserCommandService userCommandService;
    private final SocialLoginService socialLoginService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/signup")
    public ApiResponse<Void> join(@RequestBody @Valid JoinDto joinDto) {
        User user = userCommandService.join(joinDto);
        return ApiResponse.of(SuccessStatus._CREATED, null);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/signup/kakao")
    public ApiResponse<Void> getLoginWithCode(@RequestBody Map<String, String> code){
        String codeContent=code.get("code");
        String token=socialLoginService.getUserTokenFromCode(codeContent);
        return ApiResponse.of(SuccessStatus._OK, null);
    }
}
