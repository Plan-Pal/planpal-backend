package com.planpal.demo.repository;

import com.planpal.demo.domain.AddedSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddedScheduleRepository extends JpaRepository<AddedSchedule, Long> {
}
