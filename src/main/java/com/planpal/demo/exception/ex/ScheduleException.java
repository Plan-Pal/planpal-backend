package com.planpal.demo.exception.ex;

import com.planpal.demo.apipayload.status.ErrorStatus;
import com.planpal.demo.exception.GeneralException;

public class ScheduleException extends GeneralException {
    public ScheduleException(ErrorStatus errorStatus){
        super(errorStatus);
    }
}
