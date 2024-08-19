package com.surferton.server.common;

import com.surferton.server.common.dto.ErrorMessage;
import com.surferton.server.common.dto.ErrorResponse;
import com.surferton.server.exception.CustomizedException;
import com.surferton.server.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

// 발생한 에러 잡아서 정의한 에러로 할 수 있도록
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    protected ResponseEntity<ErrorResponse> handleEntityNotFoundException(NotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorResponse.of(ErrorMessage.MEMBER_NOT_FOUND_BY_ID_EXCEPTION));
    }

    @ExceptionHandler(CustomizedException.class)
    public ResponseEntity<ErrorResponse> handleUnauthorizedAccessException(CustomizedException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ErrorResponse.of(ErrorMessage.UNAUTHORIZED_ACCESS));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorResponse.of(HttpStatus.BAD_REQUEST.value(),
                Objects.requireNonNull(e.getBindingResult().getFieldError()).getDefaultMessage()));
    }
}
