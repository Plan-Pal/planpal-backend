package com.planpal.demo.exception.ex;

import com.planpal.demo.apipayload.status.ErrorStatus;
import com.planpal.demo.exception.GeneralException;

public class FriendException extends GeneralException {
    public FriendException(ErrorStatus errorStatus) {
        super(errorStatus);
    }
}
