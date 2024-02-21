package com.planpal.demo.web.dto.schedule;


import com.planpal.demo.domain.enums.ScheduleState;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateScheduleRequest {
    private String short_title;
    private String long_title;
    private String memo;
    private LocalDateTime appointed_time;
    private String place;
    private int limited_number;
    private ScheduleState schedule_state;
}

