package com.planpal.demo.service.user;

import com.planpal.demo.service.feign.KakaoTokenClient;
import com.planpal.demo.web.dto.kakao.KakaoInfo;
import com.planpal.demo.web.dto.kakao.KakaoTokenRequest;
import com.planpal.demo.web.dto.kakao.KakaoTokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SocialLoginService{

    private final KakaoTokenClient kakaoTokenClient;
    private final KakaoInfo kakaoInfo;

    public String getUserTokenFromCode(String code){
        System.out.println("code = " + code);
        String kakaoToken=getTokenFromCode(code);
        System.out.println("kakaoToken = " + kakaoToken);
        return kakaoToken;
    }

    private String getTokenFromCode(String code){
        KakaoTokenResponse token = kakaoTokenClient.getToken(
                KakaoTokenRequest.newInstance(kakaoInfo, code).toString());

        return token.getAccess_token();
    }
}
