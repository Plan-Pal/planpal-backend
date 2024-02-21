package com.planpal.demo.service.schedule;

import com.planpal.demo.converter.ScheduleConverter;
import com.planpal.demo.repository.SchedulesRepository;
import com.planpal.demo.web.dto.schedule.AddScheduleRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final SchedulesRepository schedulesRepository;

    public void saveSchedule(AddScheduleRequest request){
        schedulesRepository.save(ScheduleConverter.toSchedule(request));
    }
}
