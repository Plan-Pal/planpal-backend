package com.planpal.demo.web.controller;

import com.planpal.demo.apipayload.ApiResponse;
import com.planpal.demo.apipayload.status.SuccessStatus;
import com.planpal.demo.service.user.SocialLoginService;
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

    @PostMapping("/signup")
    public ApiResponse<Void> getLoginWithCode(@RequestBody Map<String, String> code){
        String codeContent=code.get("code");
        KakaoUserInfoDto userInfo=socialLoginService.getUserInfoFromCode(codeContent);
        return ApiResponse.of(SuccessStatus._OK, null);
    }
}
