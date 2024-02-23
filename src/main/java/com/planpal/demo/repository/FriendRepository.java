package com.planpal.demo.repository;

import com.planpal.demo.domain.User;
import com.planpal.demo.domain.mapping.Friend;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FriendRepository extends JpaRepository<Friend, Long> {
    boolean existsByUser1AndUser2(User user1, User user2);
}
