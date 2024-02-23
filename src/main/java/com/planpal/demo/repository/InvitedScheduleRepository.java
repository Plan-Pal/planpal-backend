package com.planpal.demo.repository;

import com.planpal.demo.domain.InvitedSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InvitedScheduleRepository extends JpaRepository<InvitedSchedule, Long> {
    List<InvitedSchedule> findAllByScheduleId(Long scheduleId);
}
