package com.planpal.demo.web.controller;

import com.planpal.demo.apipayload.ApiResponse;
import com.planpal.demo.apipayload.status.SuccessStatus;
import com.planpal.demo.service.schedule.ScheduleService;
import com.planpal.demo.web.dto.schedule.AddScheduleRequest;
import com.planpal.demo.web.dto.schedule.UpdateScheduleRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ScheduleRestController {
    private final ScheduleService scheduleService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/schedules")
    public ApiResponse<Void> createSchedule(@RequestBody AddScheduleRequest request){
        scheduleService.saveSchedule(request);
        return ApiResponse.of(SuccessStatus._CREATED, null);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/schedules/{scheduleId}")
    public ApiResponse<Void> updateSchedule(@PathVariable Long scheduleId, @RequestBody UpdateScheduleRequest request){
        scheduleService.updateSchedule(scheduleId, request);
        return ApiResponse.of(SuccessStatus._OK, null);
    }
}
