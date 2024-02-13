package com.planpal.demo.domain;

import com.planpal.demo.domain.common.BaseEntity;
import com.planpal.demo.domain.enums.SocialType;
import com.planpal.demo.domain.enums.AlarmState;
import com.planpal.demo.domain.enums.UserState;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 4)
    private String tagId;

    @Column(nullable = false, unique = true, length = 10)
    private String nickname;

    @Column(nullable = false, unique = true, length = 70)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 5)
    private AlarmState alarmState;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private SocialType socialType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private UserState userState;

    private LocalDate inactiveAt;

    @Builder
    public User(String nickname, String email, SocialType socialType) {
        this.tagId = generateRandomTagId();
        this.nickname = nickname;
        this.email = email;
        this.socialType = socialType;
        this.alarmState = AlarmState.OFF;
        this.userState = UserState.ACTIVE;
    }

    private String generateRandomTagId() {
        return UUID.randomUUID().toString().substring(0, 4);
    }
}
