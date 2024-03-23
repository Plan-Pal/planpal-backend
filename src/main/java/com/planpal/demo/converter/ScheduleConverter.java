package com.planpal.demo.converter;

import com.planpal.demo.domain.Schedule;
import com.planpal.demo.web.dto.schedule.*;

import java.util.List;

public class ScheduleConverter {
    public static Schedule toSchedule(AddScheduleRequest request){
        return Schedule.builder()
                .shortTitle(request.getShortTitle())
                .longTitle(request.getLongTitle())
                .memo(request.getMemo())
                .appointedTime(request.getAppointedTime())
                .place(request.getPlace())
                .limitedNumber(request.getLimitedNumber())
                .scheduleState(request.getScheduleState())
                .build();
    }

    public static GetScheduleResponse toGetScheduleResponse(Schedule schedule, List<SimpleUserInfo> scheduleUserIdList){
        return GetScheduleResponse.builder()
                .shortTitle(schedule.getShortTitle())
                .longTitle(schedule.getLongTitle())
                .memo(schedule.getMemo())
                .appointedTime(schedule.getAppointedTime())
                .place(schedule.getPlace())
                .scheduleState(schedule.getScheduleState())
                .participatingUserList(scheduleUserIdList)
                .build();
    }

    public static GetSimpleScheduleResponse toSimpleSchedule(Schedule schedule){
        return GetSimpleScheduleResponse.builder()
                .scheduleId(schedule.getId())
                .shortTitle(schedule.getShortTitle())
                .title(schedule.getLongTitle())
                .appointedTime(schedule.getAppointedTime())
                .place(schedule.getPlace())
                .build();
    }

    public static GetAllScheduleListResponse toSimpleScheduleList(List<GetSimpleScheduleResponse> schedules){
        return GetAllScheduleListResponse.builder()
                .scheduleCount(schedules.size())
                .schedules(schedules)
                .build();
    }

    public static GetAllInvitedScheduleListResponse toSimpleInvitedScheduleList(List<GetSimpleScheduleResponse> schedules){
        return GetAllInvitedScheduleListResponse.builder()
                .scheduleCount(schedules.size())
                .schedules(schedules)
                .build();
    }
}
