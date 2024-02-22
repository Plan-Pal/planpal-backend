package com.planpal.demo.web.dto.schedule;

import com.planpal.demo.domain.enums.ScheduleState;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AddScheduleRequest {
    private String shortTitle;
    private String longTitle;
    private String memo;
    private LocalDateTime appointedTime;
    private String place;
    private int limitedNumber;
    private ScheduleState scheduleState;
}
