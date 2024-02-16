package com.planpal.demo.web.dto.kakao;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "oauth2.kakao")
public class KakaoInfo {
    private String baseUrl;
    private String clientId;
    private String redirectUri;

    public String kakaoUrlInit() {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(getBaseUrl() + "/oauth/authorize")
                .queryParam("redirect_uri", getRedirectUri())
                .queryParam("client_id", getClientId())
                .queryParam("response_type", "code");

        return builder.toUriString();
    }
}
