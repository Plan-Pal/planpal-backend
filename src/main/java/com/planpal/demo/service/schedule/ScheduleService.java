package com.planpal.demo.service.schedule;

import com.planpal.demo.apipayload.status.ErrorStatus;
import com.planpal.demo.converter.ScheduleConverter;
import com.planpal.demo.domain.Schedule;
import com.planpal.demo.exception.ex.ScheduleException;
import com.planpal.demo.repository.SchedulesRepository;
import com.planpal.demo.web.dto.schedule.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ScheduleService {
    private final SchedulesRepository schedulesRepository;

    public void saveSchedule(AddScheduleRequest request){
        schedulesRepository.save(ScheduleConverter.toSchedule(request));
    }

    public void updateSchedule(Long scheduleId, UpdateScheduleRequest request){
        Schedule findSchedule = schedulesRepository.findById(scheduleId)
                .orElseThrow(() -> new ScheduleException(ErrorStatus.SCHEDULE_NOT_FOUND));
        findSchedule.update(request);
    }

    public GetScheduleResponse getSchedule(Long scheduleId){
        Schedule schedule=schedulesRepository.findById(scheduleId)
                        .orElseThrow(() -> new ScheduleException(ErrorStatus.SCHEDULE_NOT_FOUND));
        return ScheduleConverter.toGetScheduleResponse(schedule);
    }
    
    public GetAllScheduleListResponse getAllSimpleScheduls(){
        List<Schedule> schedules = schedulesRepository.findAll();
        List<GetSimpleScheduleResponse> simpleSchedules=new ArrayList<>();
        for (Schedule schedule : schedules){
            GetSimpleScheduleResponse simplesSchedule=ScheduleConverter.toSimpleSchedule(schedule);
            simpleSchedules.add(simplesSchedule);
        }

        return ScheduleConverter.toSimpleScheduleList(simpleSchedules);
    }
}
