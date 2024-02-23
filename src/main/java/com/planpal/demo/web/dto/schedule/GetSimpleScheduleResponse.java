package com.planpal.demo.web.dto.schedule;

import com.planpal.demo.domain.enums.ScheduleState;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class GetSimpleScheduleResponse {
    private final String title;
    private final LocalDateTime appointedTime;
    private final String place;
    private final ScheduleState scheduleState;

    @Builder
    public GetSimpleScheduleResponse(String title,
                                     LocalDateTime appointedTime,
                                     String place,
                                     ScheduleState scheduleState){
        this.title=title;
        this.appointedTime=appointedTime;
        this.place=place;
        this.scheduleState=scheduleState;
    }
}
