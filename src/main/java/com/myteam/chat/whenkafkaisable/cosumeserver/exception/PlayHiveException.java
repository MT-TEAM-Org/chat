package com.myteam.chat.whenkafkaisable.cosumeserver.exception;

import java.util.Map;

import com.myteam.chat.currentChatDirectory.exception.ErrorCode;
import lombok.Getter;

@Getter
public class PlayHiveException extends RuntimeException {

    private final com.myteam.chat.currentChatDirectory.exception.ErrorCode errorCode;
    private Map<String, String> errorMap;

    /**
     * @param errorCode ErrorCode에 정의된 메시지 반환
     */
    public PlayHiveException(com.myteam.chat.currentChatDirectory.exception.ErrorCode errorCode) {
        super(errorCode.getMsg());
        this.errorCode = errorCode;
    }

    /**
     * @param errorCode ErrorCode 에 정의된 메시지 반환
     * @param errorMap 필드에 대한 에러를 담은 Map
     */
    public PlayHiveException(com.myteam.chat.currentChatDirectory.exception.ErrorCode errorCode, Map<String, String> errorMap) {
        super(errorCode.getMsg());
        this.errorCode = errorCode;
        this.errorMap = errorMap;
    }

    /**
     * @param errorCode ErrorCode에 정의된 메시지 반환
     * @param message 정의되지 않은 예외 처리
     */
    public PlayHiveException(com.myteam.chat.currentChatDirectory.exception.ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public PlayHiveException(ErrorCode errorCode, Object... args) {
        super(errorCode.getFormattedMessage(args));
        this.errorCode = errorCode;
    }
}
