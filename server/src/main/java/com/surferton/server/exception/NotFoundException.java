package com.surferton.server.exception;


import com.surferton.server.common.dto.ErrorMessage;

public class NotFoundException extends BusinessException {

    public NotFoundException(ErrorMessage errorMessage) {
        super(errorMessage);
    }
}
