package com.planpal.demo.service.schedule;

import com.planpal.demo.apipayload.status.ErrorStatus;
import com.planpal.demo.converter.ScheduleConverter;
import com.planpal.demo.domain.AddedSchedule;
import com.planpal.demo.domain.InvitedSchedule;
import com.planpal.demo.domain.Schedule;
import com.planpal.demo.domain.User;
import com.planpal.demo.domain.enums.ScheduleState;
import com.planpal.demo.exception.ex.ScheduleException;
import com.planpal.demo.exception.ex.UserException;
import com.planpal.demo.repository.AddedScheduleRepository;
import com.planpal.demo.repository.InvitedScheduleRepository;
import com.planpal.demo.repository.SchedulesRepository;
import com.planpal.demo.repository.UserRepository;
import com.planpal.demo.web.dto.schedule.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ScheduleService {
    private final SchedulesRepository schedulesRepository;
    private final UserRepository userRepository;
    private final InvitedScheduleRepository invitedScheduleRepository;
    private final AddedScheduleRepository addedScheduleRepository;

    /*
    * 일정 저장
    * */
    public void saveSchedule(Long invitorId, AddScheduleRequest request){
        Schedule schedule = ScheduleConverter.toSchedule(request);
        User inviter=userRepository.findById(invitorId)
                .orElseThrow(() -> new UserException(ErrorStatus.USER_NOT_FOUND));

        AddedSchedule addedSchedule = AddedSchedule.builder()
                .user(inviter)
                .schedule(schedule)
                .isChecked(true)
                .build();
        inviter.getAddedSchedules().add(addedSchedule);
        schedule.getAddedSchedules().add(addedSchedule);

        if (request.getScheduleState() == ScheduleState.PUBLIC){
            for (Long invitedUserId : request.getInvitedIdList()){
                User user=userRepository.findById(invitedUserId)
                        .orElseThrow(() -> new UserException(ErrorStatus.USER_NOT_FOUND));

                InvitedSchedule invitedSchedule = InvitedSchedule.builder()
                        .user(user)
                        .schedule(schedule)
                        .build();

                user.getInvitedSchedules().add(invitedSchedule);
                schedule.getInvitedSchedules().add(invitedSchedule);
            }
        }

        schedulesRepository.save(schedule);
    }

    /*
    * 일정 수정
    * */
    public void updateSchedule(Long scheduleId, UpdateScheduleRequest request){
        Schedule findSchedule = schedulesRepository.findById(scheduleId)
                .orElseThrow(() -> new ScheduleException(ErrorStatus.SCHEDULE_NOT_FOUND));
        findSchedule.update(request);
    }

    /*
    * 하나의 일정 조회
    * */
    public GetScheduleResponse getSchedule(Long scheduleId){
        Schedule schedule=schedulesRepository.findById(scheduleId)
                        .orElseThrow(() -> new ScheduleException(ErrorStatus.SCHEDULE_NOT_FOUND));
        return ScheduleConverter.toGetScheduleResponse(schedule);
    }

    /*
    * 간단 일정 전체 조회
    * */
    public GetAllScheduleListResponse getAllSimpleScheduls(){
        List<Schedule> schedules = schedulesRepository.findAll();
        List<GetSimpleScheduleResponse> simpleSchedules=new ArrayList<>();
        for (Schedule schedule : schedules){
            GetSimpleScheduleResponse simplesSchedule=ScheduleConverter.toSimpleSchedule(schedule);
            simpleSchedules.add(simplesSchedule);
        }

        return ScheduleConverter.toSimpleScheduleList(simpleSchedules);
    }
}
