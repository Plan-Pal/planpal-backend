package com.planpal.demo.apipayload.status;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorStatus {

    // COMMON
    _INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON500", "서버 에러, 관리자에게 문의 바랍니다"),
    _BAD_REQUEST(HttpStatus.BAD_REQUEST,"COMMON400","잘못된 요청입니다"),
    _UNAUTHORIZED(HttpStatus.UNAUTHORIZED,"COMMON401","인증이 필요합니다"),
    _FORBIDDEN(HttpStatus.FORBIDDEN, "COMMON403", "금지된 요청입니다"),

    // USER
    USER_NOT_FOUND(HttpStatus.BAD_REQUEST, "USER4001", "사용자 정보를 찾을 수 없습니다"),
    NOT_APPLY_MYSELF(HttpStatus.BAD_REQUEST, "USER4002", "본인에게 신청할 수 없습니다"),

    // FRIEND
    FRIEND_NOT_FOUND(HttpStatus.BAD_REQUEST, "FRIEND4001", "친구 정보를 찾을 수 없습니다"),
    FRIEND_REQUEST_ALREADY_EXISTS(HttpStatus.BAD_REQUEST, "FRIEND4002", "친구 신청 정보가 이미 존재합니다"),
    FRIEND_REQUEST_NOT_FOUND(HttpStatus.BAD_REQUEST, "FRIEND4003", "친구 신청 정보를 찾을 수 없습니다"),
    FRIEND_ALREADY_EXISTS(HttpStatus.BAD_REQUEST, "FRIEND4004", "이미 친구 상태입니다"),
    RECEIVED_FRIEND_REQUEST_ALREADY_EXISTS(HttpStatus.BAD_REQUEST, "FRIEND4005", "해당 사용자로부터의 친구 요청 정보가 이미 존재합니다"),

    // JWT
    TOKEN_INVALID(HttpStatus.UNAUTHORIZED, "JWT4001", "인증에 실패하였습니다"),
    TOKEN_EXPIRED(HttpStatus.UNAUTHORIZED, "JWT4002", "토큰이 만료되었습니다"),

    // SCHEDULE
    SCHEDULE_NOT_FOUND(HttpStatus.BAD_REQUEST, "SCHEDULE4001", "일정 정보를 찾을 수 없습니다"),
    INVITED_SCHEDULE_NOT_FOUND(HttpStatus.BAD_REQUEST, "SCHEDULE4002", "초대 일정 정보를 찾을 수 없습니다"),
    UNAUTHORIZED_USER_ACCESS(HttpStatus.BAD_REQUEST, "SCHEDULE4003", "해당 일정에 접근 권한이 없습니다.")
    ;

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}