package com.planpal.demo.repository;

import com.planpal.demo.domain.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchedulesRepository extends JpaRepository<Schedule, Long> {
}
