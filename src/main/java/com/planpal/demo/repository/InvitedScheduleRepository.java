package com.planpal.demo.repository;

import com.planpal.demo.domain.InvitedSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvitedScheduleRepository extends JpaRepository<InvitedSchedule, Long> {
}
