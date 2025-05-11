package com.myteam.chat.global.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ErrorResponse<T> {
    private final String timestamp = String.valueOf(LocalDateTime.now());
    private final HttpStatus status;
    private final String message;
    private final T data;

    @Builder
    public ErrorResponse(HttpStatus status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }
}
