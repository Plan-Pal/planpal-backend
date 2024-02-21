package com.planpal.demo.converter;

import com.planpal.demo.domain.Schedule;
import com.planpal.demo.web.dto.schedule.AddScheduleRequest;
import com.planpal.demo.web.dto.schedule.GetAllScheduleListResponse;
import com.planpal.demo.web.dto.schedule.GetScheduleResponse;
import com.planpal.demo.web.dto.schedule.GetSimpleScheduleResponse;

import java.util.List;

public class ScheduleConverter {
    public static Schedule toSchedule(AddScheduleRequest request){
        return Schedule.builder()
                .short_title(request.getShort_title())
                .long_title(request.getLong_title())
                .memo(request.getMemo())
                .appointed_time(request.getAppointed_time())
                .place(request.getPlace())
                .limited_number(request.getLimited_number())
                .schedule_state(request.getSchedule_state())
                .build();
    }

    public static GetScheduleResponse toGetScheduleResponse(Schedule schedule){
        return GetScheduleResponse.builder()
                .short_title(schedule.getShort_title())
                .long_title(schedule.getLong_title())
                .memo(schedule.getMemo())
                .appointed_time(schedule.getAppointed_time())
                .place(schedule.getPlace())
                .scheduleState(schedule.getSchedule_state())
                .build();
    }

    public static GetSimpleScheduleResponse toSimpleSchedule(Schedule schedule){
        return GetSimpleScheduleResponse.builder()
                .title(schedule.getLong_title())
                .appointed_time(schedule.getAppointed_time())
                .place(schedule.getPlace())
                .scheduleState(schedule.getSchedule_state())
                .build();
    }

    public static GetAllScheduleListResponse toSimpleScheduleList(List<GetSimpleScheduleResponse> schedules){
        return GetAllScheduleListResponse.builder()
                .schedule_count(schedules.size())
                .schedules(schedules)
                .build();
    }
}
