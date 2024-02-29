package com.planpal.demo.auth.jwt;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@RequiredArgsConstructor
@ConfigurationProperties("jwt")
public class JwtProperties {
    private final String secretKey;
    private final Integer accessTokenExpiresIn;
    private final Integer refreshTokenExpiresIn;
}
