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
    @JoinColumn(name = "sender_id")
    private User sender;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receiver_id")
    private User receiver;

    public FriendRequest(User sender, User receiver) {
        this.sender = sender;
        sender.getSentRequests().add(this);

        this.receiver = receiver;
        receiver.getReceivedRequests().add(this);
    }
}
