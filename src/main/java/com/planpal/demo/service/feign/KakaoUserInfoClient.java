package com.planpal.demo.service.feign;

import com.planpal.demo.config.KakaoFeignConfig;
import com.planpal.demo.web.dto.kakao.KakaoUserInfoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "kakaoInfoClient", url = "https://kapi.kakao.com", configuration = KakaoFeignConfig.class)
@Component
public interface KakaoUserInfoClient {

    @GetMapping(value = "/v2/user/me")
    KakaoUserInfoResponse getUserInfo(@RequestHeader(name = "Authorization") String Authorization);
}
