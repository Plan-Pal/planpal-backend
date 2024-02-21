package com.planpal.demo.web.dto.schedule;

import com.planpal.demo.domain.enums.ScheduleState;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class GetSimpleScheduleResponse {
    private final String title;
    private final LocalDateTime appointed_time;
    private final String place;
    private final ScheduleState scheduleState;

    @Builder
    public GetSimpleScheduleResponse(String title,
                                     LocalDateTime appointed_time,
                                     String place,
                                     ScheduleState scheduleState){
        this.title=title;
        this.appointed_time=appointed_time;
        this.place=place;
        this.scheduleState=scheduleState;
    }
}
