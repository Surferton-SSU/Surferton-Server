package com.surferton.server.exception;


import com.surferton.server.common.dto.ErrorMessage;

public class CustomizedException extends BusinessException {

    public CustomizedException(ErrorMessage errorMessage) {
        super(errorMessage);
    }
}
