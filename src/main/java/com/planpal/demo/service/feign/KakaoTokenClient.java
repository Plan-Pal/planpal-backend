package com.planpal.demo.service.feign;

import com.planpal.demo.config.KakaoFeignConfig;
import com.planpal.demo.web.dto.kakao.KakaoTokenResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "kakaoTokenClient", url = "https://kauth.kakao.com", configuration = KakaoFeignConfig.class)
@Component
public interface KakaoTokenClient {
    @PostMapping(value = "/oauth/token")
    KakaoTokenResponse getToken(@RequestBody String kakaoTokenRequestDto);
}