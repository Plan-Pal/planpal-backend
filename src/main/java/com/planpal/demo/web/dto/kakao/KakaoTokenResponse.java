package com.planpal.demo.web.dto.kakao;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class KakaoTokenResponse {
    private String token_type; // 반환되는 토큰 타입 (Bearer)
    private String access_token;
    private String expires_in; // accessToken 만료 시간
    private String refresh_token;
    private String refresh_token_expires_in; // refreshToken 만료 시간
    private String scope;
    private String id_token;

    public String getAccess_token() {
        return "Bearer " + access_token;
    }
}

