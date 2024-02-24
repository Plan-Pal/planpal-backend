package com.planpal.demo.repository;

import com.planpal.demo.domain.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SchedulesRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findAllByIdIn(List<Long> scheduleIds);
}
