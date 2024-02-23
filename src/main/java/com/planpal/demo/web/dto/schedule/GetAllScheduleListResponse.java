package com.planpal.demo.web.dto.schedule;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class GetAllScheduleListResponse {
    private final int scheduleCount;
    private final List<GetSimpleScheduleResponse> schedules;

    @Builder
    public GetAllScheduleListResponse(int scheduleCount,
                                   List<GetSimpleScheduleResponse> schedules){
        this.scheduleCount=scheduleCount;
        this.schedules=schedules;
    }
}
