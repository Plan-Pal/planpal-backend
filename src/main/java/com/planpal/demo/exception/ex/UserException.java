package com.planpal.demo.exception.ex;

import com.planpal.demo.apipayload.status.ErrorStatus;
import com.planpal.demo.exception.GeneralException;

public class UserException extends GeneralException {
    public UserException(ErrorStatus errorStatus) {
        super(errorStatus);
    }
}
