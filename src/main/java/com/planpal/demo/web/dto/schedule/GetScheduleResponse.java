package com.planpal.demo.web.dto.schedule;

import com.planpal.demo.domain.enums.ScheduleState;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class GetScheduleResponse {
    private final String short_title;
    private final String long_title;
    private final String memo;
    private final LocalDateTime appointed_time;
    private final String place;
    private final ScheduleState scheduleState;

    @Builder
    public GetScheduleResponse(String short_title,
                               String long_title,
                               String memo,
                               LocalDateTime appointed_time,
                               String place,
                               ScheduleState scheduleState){
        this.short_title=short_title;
        this.long_title=long_title;
        this.memo=memo;
        this.appointed_time=appointed_time;
        this.place=place;
        this.scheduleState=scheduleState;
    }
}
