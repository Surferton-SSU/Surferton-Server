package com.surferton.server.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum SuccessMessage {

    POST_SUCCESS(HttpStatus.CREATED.value(), "등록이 완료되었습니다."),
    WISH_POST_SUCCESS(HttpStatus.OK.value(), "위시 리스트에 성공적으로 추가되었습니다."),
    WISH_DELETE_SUCCESS(HttpStatus.OK.value(), "위시 리스트에 성공적으로 취소되었습니다."),
    WISHLIST_GET_SUCCESS(HttpStatus.OK.value(), "위시 리스트가 성공적으로 반환되었습니다."),
    ROOM_BOOKING_POST_SUCCESS(HttpStatus.CREATED.value(), "예약에 성공하였습니다."),
    ;
    private final int status;
    private final String message;

}
