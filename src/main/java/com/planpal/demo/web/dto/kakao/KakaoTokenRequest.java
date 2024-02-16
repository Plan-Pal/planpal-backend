package com.planpal.demo.web.dto.kakao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class KakaoTokenRequest {

    private String code;
    private String client_id;
    private String client_secret;
    private String redirect_uri;
    private final String grant_type = "authorization_code";

    public static KakaoTokenRequest newInstance(KakaoInfo kakaoInfo, String code) {
        return KakaoTokenRequest.builder()
                .client_id(kakaoInfo.getClientId())
                .redirect_uri(kakaoInfo.getRedirectUri())
                .code(code)
                .build();
    }

    @Override
    public String toString() {
        return
                "code=" + code + '&' +
                        "client_id=" + client_id + '&' +
                        "client_secret=" + client_secret + '&' +
                        "redirect_uri=" + redirect_uri + '&' +
                        "grant_type=" + grant_type;
    }
}
