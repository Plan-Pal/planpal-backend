package com.planpal.demo.web.controller;

import com.planpal.demo.apipayload.ApiResponse;
import com.planpal.demo.apipayload.status.SuccessStatus;
import com.planpal.demo.service.schedule.ScheduleService;
import com.planpal.demo.web.dto.schedule.GetScheduleResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ScheduleGetController {
    private final ScheduleService scheduleService;

    @GetMapping("/schedules/{scheduleId}")
    public ApiResponse<GetScheduleResponse> getSchedule(@PathVariable Long scheduleId){
        GetScheduleResponse response=scheduleService.getSchedule(scheduleId);
        return ApiResponse.of(SuccessStatus._OK, response);
    }
}
