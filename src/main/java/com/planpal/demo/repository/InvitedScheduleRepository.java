package com.planpal.demo.repository;

import com.planpal.demo.domain.InvitedSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InvitedScheduleRepository extends JpaRepository<InvitedSchedule, Long> {
    List<InvitedSchedule> findAllByScheduleId(Long scheduleId);
    Optional<InvitedSchedule> findByUserIdAndScheduleId(Long userId, Long scheduleId);
    boolean existsByUserIdAndScheduleId(Long userId, Long scheduleId);
    List<InvitedSchedule> findByUserId(Long userId);
}
