package com.example.chatdemo.api.common;

import org.springframework.http.HttpStatus;

public class BusinessException extends RuntimeException {

    private final ErrorCode errorCode;
    private final HttpStatus httpStatus;

    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
        this.httpStatus = mapStatus(errorCode);
    }

    public BusinessException(ErrorCode errorCode, String detail) {
        super(detail);
        this.errorCode = errorCode;
        this.httpStatus = mapStatus(errorCode);
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    private static HttpStatus mapStatus(ErrorCode errorCode) {
        int code = errorCode.getCode();
        if (code >= 50000) return HttpStatus.INTERNAL_SERVER_ERROR;
        if (code >= 40400) return HttpStatus.NOT_FOUND;
        if (code >= 40300) return HttpStatus.FORBIDDEN;
        if (code >= 40100) return HttpStatus.UNAUTHORIZED;
        return HttpStatus.BAD_REQUEST;
    }
}
