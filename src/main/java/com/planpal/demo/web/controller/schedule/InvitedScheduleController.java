package com.planpal.demo.web.controller.schedule;

import com.planpal.demo.apipayload.ApiResponse;
import com.planpal.demo.apipayload.status.SuccessStatus;
import com.planpal.demo.service.schedule.InvitedScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class InvitedScheduleController {
    private final InvitedScheduleService invitedScheduleService;

    @PostMapping("/schedules/accept/{scheduleId}")
    public ApiResponse<Void> acceptSchedule(@PathVariable Long scheduleId,
                                            @AuthenticationPrincipal Long acceptorId){
        invitedScheduleService.acceptSchedule(scheduleId, acceptorId);
        return ApiResponse.of(SuccessStatus._OK, null);
    }
}
