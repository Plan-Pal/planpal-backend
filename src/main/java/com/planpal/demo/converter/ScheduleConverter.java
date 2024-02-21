package com.planpal.demo.converter;

import com.planpal.demo.domain.Schedule;
import com.planpal.demo.web.dto.schedule.AddScheduleRequest;

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
}
