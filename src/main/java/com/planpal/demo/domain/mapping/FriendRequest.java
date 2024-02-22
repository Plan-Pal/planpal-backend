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
public class FriendRequest extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "inviter_id")
    private User inviter;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "invitee_id")
    private User invitee;

    public FriendRequest(User inviter, User invitee) {
        this.inviter = inviter;
        inviter.getInviteList().add(this);

        this.invitee = invitee;
        invitee.getInvitedList().add(this);
    }
}
