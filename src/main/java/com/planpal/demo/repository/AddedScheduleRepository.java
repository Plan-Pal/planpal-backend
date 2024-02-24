package com.planpal.demo.repository;

import com.planpal.demo.domain.AddedSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddedScheduleRepository extends JpaRepository<AddedSchedule, Long> {
    boolean existsByUserIdAndScheduleId(Long userId, Long scheduleId);
    List<AddedSchedule> findByUserId(Long userId);
    List<AddedSchedule> findByScheduleId(Long scheduleId);
}
