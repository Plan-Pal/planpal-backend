package com.planpal.demo.service.schedule;

import com.planpal.demo.apipayload.status.ErrorStatus;
import com.planpal.demo.converter.ScheduleConverter;
import com.planpal.demo.domain.Schedule;
import com.planpal.demo.exception.ex.ScheduleException;
import com.planpal.demo.repository.SchedulesRepository;
import com.planpal.demo.web.dto.schedule.AddScheduleRequest;
import com.planpal.demo.web.dto.schedule.GetScheduleResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final SchedulesRepository schedulesRepository;

    public void saveSchedule(AddScheduleRequest request){
        schedulesRepository.save(ScheduleConverter.toSchedule(request));
    }

    public GetScheduleResponse getSchedule(Long scheduleId){
        Schedule schedule=schedulesRepository.findById(scheduleId)
                        .orElseThrow(() -> new ScheduleException(ErrorStatus.SCHEDULE_NOT_FOUND));
        return ScheduleConverter.toGetScheduleResponse(schedule);
    }
}
