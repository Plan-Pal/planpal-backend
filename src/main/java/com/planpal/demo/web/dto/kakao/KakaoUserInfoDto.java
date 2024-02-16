package com.planpal.demo.web.dto.kakao;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class KakaoUserInfoDto {
    private final Long id;
    private final String nickname;
}
