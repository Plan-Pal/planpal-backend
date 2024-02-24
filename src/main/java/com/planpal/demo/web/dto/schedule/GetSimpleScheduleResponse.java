package com.planpal.demo.web.dto.schedule;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class GetSimpleScheduleResponse {
    private final String title;
    private final LocalDateTime appointedTime;
    private final String place;

    @Builder
    public GetSimpleScheduleResponse(String title,
                                     LocalDateTime appointedTime,
                                     String place){
        this.title=title;
        this.appointedTime=appointedTime;
        this.place=place;
    }
}
