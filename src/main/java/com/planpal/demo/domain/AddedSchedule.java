package com.planpal.demo.domain;

import com.planpal.demo.domain.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "added_schedule_uk",
                        columnNames = {"user_id", "schedule_id"})
        })
public class AddedSchedule extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;

    @Setter
    @Column(nullable = false)
    private Boolean isChecked;

    @Builder
    public AddedSchedule(User user, Schedule schedule, Boolean isChecked){
        this.user=user;
        this.schedule=schedule;
        this.isChecked=isChecked;
    }
}
