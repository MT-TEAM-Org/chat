package com.myteam.chat.global.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public enum ErrorCode {

    // 500 Server Error
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "PlayHive Server Error"),
    // 503 Service Unavailable
    KAFKA_TOPIC_DELETE_FAILED(HttpStatus.SERVICE_UNAVAILABLE, "Failed to delete the Kafka topic."),

    // 400 Bad Request
    INVALID_PARAMETER(HttpStatus.BAD_REQUEST, "Invalid parameter value"),

    // 401
    INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "Invalid Token"),
    MISSING_AUTH_HEADER(HttpStatus.UNAUTHORIZED, "No Authorization header or not Bearer type");

    private final HttpStatus status;
    private final String msg;

    ErrorCode(HttpStatus status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    /**
     * 동적인 메시지 생성 (예: TTL 값을 포함)
     */
    public String getFormattedMessage(Object... args) {
        return String.format(this.msg.replace("{ttl}", "%s"), args);
    }
}
