package com.surferton.server.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorMessage {

    MEMBER_NOT_FOUND_BY_ID_EXCEPTION(HttpStatus.NOT_FOUND.value(), "ID에 해당하는 사용자가 존재하지 않습니다."),
    UNAUTHORIZED_ACCESS(HttpStatus.UNAUTHORIZED.value(), "접근 권한이 없습니다."),
    ROOM_NOT_FOUND_BY_ID_EXCEPTION(HttpStatus.NOT_FOUND.value(), "ID에 해당하는 방이 존재하지 않습니다."),
    INVALID_CHECKIN_CHECKOUT_DATE_EXCEPTION(HttpStatus.BAD_REQUEST.value(), "체크인 날짜는 체크아웃 날짜보다 이전이어야 합니다.");
    private final int status;
    private final String message;

}
