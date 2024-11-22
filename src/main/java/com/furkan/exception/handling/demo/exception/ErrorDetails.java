package com.furkan.exception.handling.demo.exception;

import java.util.Map;

public record  ErrorDetails(
        String message,
        ErrorCode errorCode,
        Map<String,String> validationErrors
) {
    public static ErrorDetails of(ErrorCode errorCode) {
        return new ErrorDetails(errorCode.getDefaultMessage(), errorCode,null);
    }

    public static ErrorDetails of(String message, ErrorCode errorCode) {
        return new ErrorDetails(message, errorCode,null);
    }
    public static ErrorDetails ofValidation(Map<String, String> validationErrors) {
        return new ErrorDetails(
                ErrorCode.VALIDATION_ERROR.getDefaultMessage(),
                ErrorCode.VALIDATION_ERROR,
                validationErrors
        );
    }
}
