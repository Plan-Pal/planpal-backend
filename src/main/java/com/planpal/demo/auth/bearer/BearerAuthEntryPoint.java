package com.planpal.demo.auth.bearer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.planpal.demo.apipayload.ApiResponse;
import com.planpal.demo.apipayload.status.ErrorStatus;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Slf4j
public class BearerAuthEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper = new ObjectMapper()
            .setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        Object exception = request.getAttribute("exception");
        if (exception instanceof ExpiredJwtException) {
            response.getWriter().write(createResponseBody(ErrorStatus.TOKEN_EXPIRED));
        }
        else {
            response.getWriter().write(createResponseBody(ErrorStatus.TOKEN_INVALID));
        }

    }

    private String createResponseBody(ErrorStatus status) throws JsonProcessingException {
        ApiResponse<Void> apiResponse = ApiResponse.onFailure(status, null);
        return objectMapper.writeValueAsString(apiResponse);
    }
}
