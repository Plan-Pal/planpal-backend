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
    private String short_title;

    @Column(nullable = false, length = 20)
    private String long_title;

    @Column(length = 100)
    private String memo;

    @Column(nullable = false)
    private LocalDateTime appointed_time;

    @Column(nullable = false, length = 40)
    private String place;

    @Column
    private int limited_number;

    @Column(nullable = false)
    private ScheduleState schedule_state;

    @Builder
    public Schedule(String short_title, String long_title, String memo, LocalDateTime appointed_time, String place, int limited_number, ScheduleState schedule_state){
        this.short_title=short_title;
        this.long_title=long_title;
        this.memo=memo;
        this.appointed_time=appointed_time;
        this.place=place;
        this.limited_number=limited_number;
        this.schedule_state=schedule_state;
    }

    public void update(UpdateScheduleRequest request){
        this.short_title=request.getShort_title();
        this.long_title= request.getLong_title();
        this.memo=request.getMemo();
        this.appointed_time=request.getAppointed_time();
        this.place= request.getPlace();
        this.limited_number=request.getLimited_number();
        this.schedule_state=request.getSchedule_state();
    }
}



