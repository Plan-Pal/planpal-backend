package com.planpal.demo.exception;

import com.planpal.demo.apipayload.status.ErrorStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GeneralException extends RuntimeException {
    private final ErrorStatus errorStatus;
}
