package com.example.chatdemo.api.common;

public enum ErrorCode {

    BAD_REQUEST(40001, "Bad request"),
    VALIDATION_ERROR(40002, "Validation failed"),
    UNAUTHORIZED(40101, "Authentication required"),
    FORBIDDEN(40301, "Access denied"),
    NOT_FOUND(40401, "Resource not found"),
    TOKEN_EXPIRED(40102, "Token expired"),
    TOKEN_INVALID(40103, "Token invalid"),
    SAFETY_REJECTED(40003, "Content rejected by safety filter"),

    CHARACTER_NOT_FOUND(40402, "Character not found"),
    SOURCE_NOT_FOUND(40403, "Source not found"),
    MEMORY_NOT_FOUND(40404, "Memory not found"),
    SESSION_NOT_FOUND(40405, "Session not found"),

    LLM_ERROR(50001, "LLM service error"),
    INTERNAL_ERROR(50002, "Internal server error");

    private final int code;
    private final String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
