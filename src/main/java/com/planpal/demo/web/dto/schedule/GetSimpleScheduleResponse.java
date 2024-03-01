package com.planpal.demo.web.dto.schedule;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class GetSimpleScheduleResponse {
    private final String shortTitle;
    private final String title;
    private final LocalDateTime appointedTime;
    private final String place;

    @Builder
    public GetSimpleScheduleResponse(String shortTitle,
                                     String title,
                                     LocalDateTime appointedTime,
                                     String place){
        this.shortTitle=shortTitle;
        this.title=title;
        this.appointedTime=appointedTime;
        this.place=place;
    }
}
