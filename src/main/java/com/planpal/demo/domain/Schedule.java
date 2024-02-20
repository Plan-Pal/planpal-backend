package com.planpal.demo.domain;

import com.planpal.demo.domain.enums.ScheduleState;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Schedule {
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

    @Column(nullable = false)
    private int limited_number;

    @Column(nullable = false)
    private ScheduleState schedule_state;
}



