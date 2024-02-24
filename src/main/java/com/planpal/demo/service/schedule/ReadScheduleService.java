package com.planpal.demo.service.schedule;

import com.planpal.demo.apipayload.status.ErrorStatus;
import com.planpal.demo.converter.ScheduleConverter;
import com.planpal.demo.converter.UserConverter;
import com.planpal.demo.domain.AddedSchedule;
import com.planpal.demo.domain.InvitedSchedule;
import com.planpal.demo.domain.Schedule;
import com.planpal.demo.exception.ex.ScheduleException;
import com.planpal.demo.repository.AddedScheduleRepository;
import com.planpal.demo.repository.InvitedScheduleRepository;
import com.planpal.demo.repository.SchedulesRepository;
import com.planpal.demo.web.dto.schedule.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional
public class ReadScheduleService {
    private final SchedulesRepository schedulesRepository;
    private final AddedScheduleRepository addedScheduleRepository;
    private final InvitedScheduleRepository invitedScheduleRepository;

    /*
     * 하나의 일정 조회
     * */
    public GetScheduleResponse getSchedule(Long scheduleId, Long userId){
        Schedule schedule=schedulesRepository.findById(scheduleId)
                .orElseThrow(() -> new ScheduleException(ErrorStatus.SCHEDULE_NOT_FOUND));

        if (!addedScheduleRepository.existsByUserIdAndScheduleId(userId, scheduleId)
        && !invitedScheduleRepository.existsByUserIdAndScheduleId(userId, scheduleId)){
            throw new ScheduleException(ErrorStatus.UNAUTHORIZED_USER_ACCESS);
        }

        List<SimpleUserInfo> scheduleUserIdList = getUserIdsByScheduleId(scheduleId);

        return ScheduleConverter.toGetScheduleResponse(schedule, scheduleUserIdList);
    }

    private List<SimpleUserInfo> getUserIdsByScheduleId(Long scheduleId){
        List<AddedSchedule> addedSchedules = addedScheduleRepository.findByScheduleId(scheduleId);
        return addedSchedules.stream()
                .map(AddedSchedule::getUser)
                .map(UserConverter::toSimpleUserInfo)
                .collect(Collectors.toList());
    }

    /*
     * 간단 일정 전체 조회
     * */
    public GetAllScheduleListResponse getAllSimpleSchedules(Long userId){
        List<Long> userScheduleIdList = getScheduleIdsByUserId(userId);
        List<Schedule> schedules = schedulesRepository.findAllByIdIn(userScheduleIdList);
        List<GetSimpleScheduleResponse> simpleSchedules=new ArrayList<>();
        for (Schedule schedule : schedules){
            GetSimpleScheduleResponse simplesSchedule=ScheduleConverter.toSimpleSchedule(schedule);
            simpleSchedules.add(simplesSchedule);
        }

        return ScheduleConverter.toSimpleScheduleList(simpleSchedules);
    }

    private List<Long> getScheduleIdsByUserId(Long userId){
        List<AddedSchedule> addedSchedules = addedScheduleRepository.findByUserId(userId);
        return addedSchedules.stream()
                .map(addedSchedule -> addedSchedule.getSchedule().getId())
                .collect(Collectors.toList());
    }

    /*
    * 초대 받은 일정 조회
    * */
    public GetAllInvitedScheduleListResponse getAllInvitedSchedules(Long userId){
        List<Long> userScheduleIdList = getInvitedScheduleIdsByUserId(userId);
        List<Schedule> schedules = schedulesRepository.findAllByIdIn(userScheduleIdList);
        List<GetSimpleScheduleResponse> simpleSchedules=new ArrayList<>();
        for (Schedule schedule : schedules){
            GetSimpleScheduleResponse simplesSchedule=ScheduleConverter.toSimpleSchedule(schedule);
            simpleSchedules.add(simplesSchedule);
        }

        return ScheduleConverter.toSimpleInvitedScheduleList(simpleSchedules);
    }

    private List<Long> getInvitedScheduleIdsByUserId(Long userId){
        List<InvitedSchedule> invitedSchedules = invitedScheduleRepository.findByUserId(userId);
        return invitedSchedules.stream()
                .map(invitedSchedule -> invitedSchedule.getSchedule().getId())
                .collect(Collectors.toList());
    }
}
