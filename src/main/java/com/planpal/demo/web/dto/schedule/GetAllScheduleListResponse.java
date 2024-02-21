package com.planpal.demo.web.dto.schedule;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class GetAllScheduleListResponse {
    private final int schedule_count;
    private final List<GetSimpleScheduleResponse> schedules;

    @Builder
    public GetAllScheduleListResponse(int schedule_count,
                                   List<GetSimpleScheduleResponse> schedules){
        this.schedule_count=schedule_count;
        this.schedules=schedules;
    }
}
