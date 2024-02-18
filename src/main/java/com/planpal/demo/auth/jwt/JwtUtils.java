package com.planpal.demo.auth.jwt;

import com.planpal.demo.domain.User;
import com.planpal.demo.service.user.UserQueryService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtUtils {

    private final static String ACCESS_TYPE = "Access";
    private String base64EncodedSecretKey;

    private final JwtProperties jwtProperties;
    private final UserQueryService userQueryService;

    @PostConstruct
    public void init() {
        this.base64EncodedSecretKey = Base64.getEncoder().encodeToString(jwtProperties.getSecretKey().getBytes(StandardCharsets.UTF_8));
    }

    public String generateAccessToken(Long userId) {
        return generateToken(userId, ACCESS_TYPE, jwtProperties.getAccessTokenExpiresIn());
    }

    public boolean validate(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(base64EncodedSecretKey)
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Authentication getAuthentication(String token) {
        Claims claims = getClaims(token);
        Long userId = claims.get("userId", Long.class);
        User user = userQueryService.findById(userId);

        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority("ROLE_USER"));
        return new UsernamePasswordAuthenticationToken(user.getId(), null, roles);
    }

    private String generateToken(Long userId, String type, Integer tokenExpiresIn) {
        long current = System.currentTimeMillis();
        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setIssuedAt(new Date(current))
                .setSubject(type)
                .setExpiration(new Date(
                        current + tokenExpiresIn * 1000
                ))
                .claim("userId", userId)
                .signWith(SignatureAlgorithm.HS256, base64EncodedSecretKey)
                .compact();
    }

    private Claims getClaims(String token) {
        return Jwts.parser()
                .setSigningKey(base64EncodedSecretKey)
                .parseClaimsJws(token)
                .getBody();
    }
}
