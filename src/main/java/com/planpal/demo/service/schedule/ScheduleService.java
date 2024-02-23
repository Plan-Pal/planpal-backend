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
    public void updateSchedule(Long scheduleId, Long modifierId, UpdateScheduleRequest request){
        Schedule findSchedule = schedulesRepository.findById(scheduleId)
                .orElseThrow(() -> new ScheduleException(ErrorStatus.SCHEDULE_NOT_FOUND));
        findSchedule.update(request);

        List<InvitedSchedule> existingInvitedSchedules = findSchedule.getInvitedSchedules();
        List<Long> existingInvitedIds = existingInvitedSchedules.stream()
                .map(invitedSchedule -> invitedSchedule.getUser().getId())
                .toList();

        for (Long invitedId : request.getInvitedIdList()){
            if (!existingInvitedIds.contains(invitedId)){
                User invitedUser = userRepository.findById(invitedId)
                        .orElseThrow(() -> new UserException(ErrorStatus.USER_NOT_FOUND));
                InvitedSchedule newinvitedSchedule = InvitedSchedule.builder()
                        .user(invitedUser)
                        .schedule(findSchedule)
                        .build();

                findSchedule.getInvitedSchedules().add(newinvitedSchedule);
                invitedUser.getInvitedSchedules().add(newinvitedSchedule);
            }
        }

        List<AddedSchedule> existingAddedSchedules = findSchedule.getAddedSchedules();
        for (AddedSchedule addedSchedule : existingAddedSchedules){
            if (!addedSchedule.getUser().getId().equals(modifierId)){
                addedSchedule.setIsChecked(false);
            }
        }
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

    /*
     * 일정 삭제 - PRIVATE 상태라면 아예 삭제
     *          - PUBLIC 상태라면 user 연결 만 삭제 (1명 되면 PRIVATE 변환)
     *          - PUBLIC 상태이고 참여자가 1명 일 때 invited_schedule 까지 모두 삭제
     * */
    public void deleteSchedule(Long scheduleId, Long userId){
        Schedule schedule=schedulesRepository.findById(scheduleId)
                .orElseThrow(() -> new ScheduleException(ErrorStatus.SCHEDULE_NOT_FOUND));
        User user=userRepository.findById(userId)
                .orElseThrow(() -> new UserException(ErrorStatus.USER_NOT_FOUND));

        AddedSchedule deleteSchedule = schedule.getAddedSchedules().stream()
                .filter(addedSchedule -> addedSchedule.getUser().getId().equals(userId)
                        && addedSchedule.getSchedule().getId().equals(scheduleId))
                .findFirst()
                .orElseThrow(() -> new ScheduleException(ErrorStatus.SCHEDULE_NOT_FOUND));

        schedule.getAddedSchedules().remove(deleteSchedule);
        user.getAddedSchedules().remove(deleteSchedule);
        addedScheduleRepository.delete(deleteSchedule);

        if (schedule.getScheduleState() == ScheduleState.PUBLIC){
            if (schedule.getAddedSchedules().size() == 1 && schedule.getInvitedSchedules().size() == 0){
                schedule.setScheduleState(ScheduleState.PRIVATE);
            } else if (schedule.getAddedSchedules().size() == 0){
                List<InvitedSchedule> invitedScheduleList=invitedScheduleRepository.findAllByScheduleId(scheduleId);
                if (!invitedScheduleList.isEmpty()){
                    for (InvitedSchedule invitedSchedule : invitedScheduleList){
                        schedule.getInvitedSchedules().remove(invitedSchedule);
                        user.getInvitedSchedules().remove(invitedSchedule);
                        invitedScheduleRepository.delete(invitedSchedule);
                    }
                }
                schedulesRepository.delete(schedule);
            }
        } else{
            schedulesRepository.delete(schedule);
        }
    }
}
