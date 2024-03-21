package com.planpal.demo.web.dto.schedule;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class GetSimpleScheduleResponse {
    private final Long scheduleId;
    private final String shortTitle;
    private final String title;
    private final LocalDateTime appointedTime;
    private final String place;

    @Builder
    public GetSimpleScheduleResponse(Long scheduleId,
                                     String shortTitle,
                                     String title,
                                     LocalDateTime appointedTime,
                                     String place){
        this.scheduleId = scheduleId;
        this.shortTitle = shortTitle;
        this.title = title;
        this.appointedTime = appointedTime;
        this.place = place;
    }
}
