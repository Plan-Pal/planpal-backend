package com.planpal.demo.web.dto.schedule;

import com.planpal.demo.domain.enums.ScheduleState;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class GetScheduleResponse {
    private final String shortTitle;
    private final String longTitle;
    private final String memo;
    private final LocalDateTime appointedTime;
    private final String place;
    private final ScheduleState scheduleState;
    private final List<SimpleUserInfo> participatingUserList;

    @Builder
    public GetScheduleResponse(String shortTitle,
                               String longTitle,
                               String memo,
                               LocalDateTime appointedTime,
                               String place,
                               ScheduleState scheduleState,
                               List<SimpleUserInfo> participatingUserList){
        this.shortTitle=shortTitle;
        this.longTitle=longTitle;
        this.memo=memo;
        this.appointedTime=appointedTime;
        this.place=place;
        this.scheduleState=scheduleState;
        this.participatingUserList=participatingUserList;
    }
}
