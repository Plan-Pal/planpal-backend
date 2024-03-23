package com.planpal.demo.service.schedule;

import com.planpal.demo.apipayload.status.ErrorStatus;
import com.planpal.demo.domain.AddedSchedule;
import com.planpal.demo.domain.InvitedSchedule;
import com.planpal.demo.domain.Schedule;
import com.planpal.demo.domain.User;
import com.planpal.demo.exception.ex.ScheduleException;
import com.planpal.demo.exception.ex.UserException;
import com.planpal.demo.repository.InvitedScheduleRepository;
import com.planpal.demo.repository.SchedulesRepository;
import com.planpal.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class InvitedScheduleService {
    private final SchedulesRepository schedulesRepository;
    private final UserRepository userRepository;
    private final InvitedScheduleRepository invitedScheduleRepository;

    /*
    * 약속 수락
    * */
    public void acceptSchedule(Long acceptedScheduleId, Long acceptorId){
        Schedule schedule = schedulesRepository.findById(acceptedScheduleId)
                .orElseThrow(() -> new ScheduleException(ErrorStatus.SCHEDULE_NOT_FOUND));
        User user = userRepository.findById(acceptorId)
                .orElseThrow(() -> new UserException(ErrorStatus.USER_NOT_FOUND));

        InvitedSchedule invitedSchedule = invitedScheduleRepository.findByUserIdAndScheduleId(acceptorId, acceptedScheduleId)
                .orElseThrow(() -> new ScheduleException(ErrorStatus.INVITED_SCHEDULE_NOT_FOUND));
        deleteInvitedSchedule(schedule, user, invitedSchedule);

        AddedSchedule addedSchedule = AddedSchedule.builder()
                .user(user)
                .schedule(schedule)
                .isChecked(false)
                .build();
        schedule.getAddedSchedules().add(addedSchedule);
        schedule.getAddedSchedules().add(addedSchedule);
    }

    /*
    * 약속 거절
    * */
    public void refuseInvitedSchedule(Long refusedScheduleId, Long refuserId){
        Schedule schedule = schedulesRepository.findById(refusedScheduleId)
                .orElseThrow(() -> new ScheduleException(ErrorStatus.SCHEDULE_NOT_FOUND));
        User user = userRepository.findById(refuserId)
                .orElseThrow(() -> new UserException(ErrorStatus.USER_NOT_FOUND));

        InvitedSchedule invitedSchedule = invitedScheduleRepository.findByUserIdAndScheduleId(refuserId, refusedScheduleId)
                .orElseThrow(() -> new ScheduleException(ErrorStatus.INVITED_SCHEDULE_NOT_FOUND));
        deleteInvitedSchedule(schedule, user, invitedSchedule);
    }

    private void deleteInvitedSchedule(Schedule schedule, User user, InvitedSchedule invitedSchedule){
        InvitedSchedule persistentInvitedSchedule = invitedScheduleRepository.findById(invitedSchedule.getId())
                .orElseThrow(() -> new ScheduleException(ErrorStatus.INVITED_SCHEDULE_NOT_FOUND));
        schedule.getInvitedSchedules().remove(persistentInvitedSchedule);
        user.getInvitedSchedules().remove(persistentInvitedSchedule);
        invitedScheduleRepository.delete(persistentInvitedSchedule);
    }
}
