package com.planpal.demo.domain;

import com.planpal.demo.domain.common.BaseEntity;
import com.planpal.demo.domain.enums.AlarmState;
import com.planpal.demo.domain.enums.UserState;
import com.planpal.demo.domain.mapping.Friend;
import com.planpal.demo.domain.mapping.FriendRequest;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.ArrayList;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private Long kakaoId;

    @Column(nullable = false, unique = true, length = 4)
    private String tagId;

    @Column(nullable = false, length = 10)
    private String nickname;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 5)
    private AlarmState alarmState;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private UserState userState;

    private LocalDate inactiveAt;

    @OneToMany(mappedBy = "sender", cascade = CascadeType.ALL)
    private List<FriendRequest> sentRequests;

    @OneToMany(mappedBy = "receiver", cascade = CascadeType.ALL)
    private List<FriendRequest> receivedRequests;

    @OneToMany(mappedBy = "user1", fetch = FetchType.LAZY)
    private List<Friend> friendsAsUser1;

    @OneToMany(mappedBy = "user2", fetch = FetchType.LAZY)
    private List<Friend> friendsAsUser2;

    @Builder
    public User(Long kakaoId, String nickname) {
        this.kakaoId = kakaoId;
        this.nickname = nickname;
        this.tagId = generateRandomTagId();
        this.alarmState = AlarmState.OFF;
        this.userState = UserState.ACTIVE;
    }

    private String generateRandomTagId() {
        return UUID.randomUUID().toString().substring(0, 4);
    }

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<AddedSchedule> addedSchedules=new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<InvitedSchedule> invitedSchedules=new ArrayList<>();
}
