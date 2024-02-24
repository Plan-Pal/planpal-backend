package com.planpal.demo.web.controller.schedule;

import com.planpal.demo.apipayload.ApiResponse;
import com.planpal.demo.apipayload.status.SuccessStatus;
import com.planpal.demo.service.schedule.ReadScheduleService;
import com.planpal.demo.web.dto.schedule.GetAllInvitedScheduleListResponse;
import com.planpal.demo.web.dto.schedule.GetAllScheduleListResponse;
import com.planpal.demo.web.dto.schedule.GetScheduleResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ReadScheduleController {
    private final ReadScheduleService readScheduleService;

    @GetMapping("/schedules/{scheduleId}")
    public ApiResponse<GetScheduleResponse> getSchedule(@PathVariable Long scheduleId,
                                                        @AuthenticationPrincipal Long userId){
        GetScheduleResponse response=readScheduleService.getSchedule(scheduleId, userId);
        return ApiResponse.of(SuccessStatus._OK, response);
    }

    @GetMapping("/schedules")
    public ApiResponse<GetAllScheduleListResponse> getAllSchedules(@AuthenticationPrincipal Long userId){
        GetAllScheduleListResponse response = readScheduleService.getAllSimpleSchedules(userId);
        return ApiResponse.of(SuccessStatus._OK, response);
    }

    @GetMapping("/schedules/invited")
    public ApiResponse<GetAllInvitedScheduleListResponse> getAllInvitedSchedules(@AuthenticationPrincipal Long userId){
        GetAllInvitedScheduleListResponse response = readScheduleService.getAllInvitedSchedules(userId);
        return ApiResponse.of(SuccessStatus._OK, response);
    }
}
