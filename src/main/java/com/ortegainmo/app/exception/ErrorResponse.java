package com.ortegainmo.app.exception;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
public class ErrorResponse {
    private int code;
    private String status;
    private String message;
    private LocalDateTime timestamp;

    public ErrorResponse(int code, String status, String message) {
        this.code = code;
        this.status = status;
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }
}