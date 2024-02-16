package com.planpal.demo.service.user;

import com.planpal.demo.service.feign.KakaoTokenClient;
import com.planpal.demo.service.feign.KakaoUserInfoClient;
import com.planpal.demo.web.dto.kakao.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SocialLoginService{

    private final KakaoTokenClient kakaoTokenClient;
    private final KakaoUserInfoClient kakaoUserInfoClient;
    private final KakaoInfo kakaoInfo;

    public KakaoUserInfoDto getUserInfoFromCode(String code){
        String kakaoToken=getTokenFromCode(code);
        return getUserInfoFromToken(kakaoToken);
    }

    private String getTokenFromCode(String code){
        KakaoTokenResponse token = kakaoTokenClient.getToken(
                KakaoTokenRequest.newInstance(kakaoInfo, code).toString());
        return token.getAccess_token();
    }

    private KakaoUserInfoDto getUserInfoFromToken(String token){
        KakaoUserInfoResponse userinfo=kakaoUserInfoClient.getUserInfo(token);
        return KakaoUserInfoDto.builder()
                .id(userinfo.getId())
                .nickname(userinfo.getProperties().getNickname())
                .build();
    }
}
