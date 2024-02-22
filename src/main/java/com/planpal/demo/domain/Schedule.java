package com.planpal.demo.domain;

import com.planpal.demo.domain.common.BaseEntity;
import com.planpal.demo.domain.enums.ScheduleState;
import com.planpal.demo.web.dto.schedule.UpdateScheduleRequest;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Schedule extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 5)
    private String shortTitle;

    @Column(nullable = false, length = 20)
    private String longTitle;

    @Column(length = 100)
    private String memo;

    @Column(nullable = false)
    private LocalDateTime appointedTime;

    @Column(nullable = false, length = 40)
    private String place;

    @Column
    private int limitedNumber;

    @Column(nullable = false)
    private ScheduleState scheduleState;

    @Builder
    public Schedule(String shortTitle, String longTitle, String memo, LocalDateTime appointedTime, String place, int limitedNumber){
        this.shortTitle=shortTitle;
        this.longTitle=longTitle;
        this.memo=memo;
        this.appointedTime=appointedTime;
        this.place=place;
        this.limitedNumber=limitedNumber;
        this.scheduleState=ScheduleState.PRIVATE;
    }

    public void update(UpdateScheduleRequest request){
        this.shortTitle=request.getShortTitle();
        this.longTitle= request.getLongTitle();
        this.memo=request.getMemo();
        this.appointedTime=request.getAppointedTime();
        this.place= request.getPlace();
        this.limitedNumber=request.getLimitedNumber();
        this.scheduleState=request.getScheduleState();
    }
}



