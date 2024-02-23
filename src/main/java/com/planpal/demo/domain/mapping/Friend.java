package com.planpal.demo.domain.mapping;

import com.planpal.demo.domain.User;
import com.planpal.demo.domain.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Friend extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user1_id")
    private User user1;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user2_id")
    private User user2;

    public Friend(User user1, User user2) {
        this.user1 = user1;
        user1.getFriendsAsUser1().add(this);

        this.user2 = user2;
        user2.getFriendsAsUser2().add(this);
    }
}
