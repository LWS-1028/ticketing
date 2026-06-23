package com.example.ticketing.common.error;

public record ErrorResponse (
        boolean success,
        String code,
        String message
){
    public static ErrorResponse from(ErrorCode errorCode) {
        return new ErrorResponse(false, errorCode.getCode(), errorCode.getMessage());
    }
}
