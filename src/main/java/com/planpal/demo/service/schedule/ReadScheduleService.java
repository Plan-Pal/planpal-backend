package com.planpal.demo.service.schedule;

import com.planpal.demo.apipayload.status.ErrorStatus;
import com.planpal.demo.converter.ScheduleConverter;
import com.planpal.demo.domain.Schedule;
import com.planpal.demo.exception.ex.ScheduleException;
import com.planpal.demo.repository.SchedulesRepository;
import com.planpal.demo.web.dto.schedule.GetAllScheduleListResponse;
import com.planpal.demo.web.dto.schedule.GetScheduleResponse;
import com.planpal.demo.web.dto.schedule.GetSimpleScheduleResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class ReadScheduleService {
    private final SchedulesRepository schedulesRepository;
    /*
     * 하나의 일정 조회
     * */
    public GetScheduleResponse getSchedule(Long scheduleId){
        Schedule schedule=schedulesRepository.findById(scheduleId)
                .orElseThrow(() -> new ScheduleException(ErrorStatus.SCHEDULE_NOT_FOUND));
        return ScheduleConverter.toGetScheduleResponse(schedule);
    }

    /*
     * 간단 일정 전체 조회
     * */
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
