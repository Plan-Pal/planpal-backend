package com.planpal.demo.web.dto.kakao;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
public class KakaoUserInfoResponse {
    @Getter
    private Long id;
    private String connected_at;
    @Getter
    private Properties properties;
    private KakaoAccount kakaoAccount;

    @Getter
    public static class Properties{
        private String nickname;
    }

    public static class KakaoAccount{
        private Boolean profile_nickname_needs_agreement;
        private Profile profile;

        public static class Profile{
            private String nickname;
        }
    }
}
