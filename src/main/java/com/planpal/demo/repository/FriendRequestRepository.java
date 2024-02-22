package com.planpal.demo.repository;

import com.planpal.demo.domain.User;
import com.planpal.demo.domain.mapping.FriendRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FriendRequestRepository extends JpaRepository<FriendRequest, Long> {
    boolean existsBySenderAndReceiver(User sender, User receiver);
}
