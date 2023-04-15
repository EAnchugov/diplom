package ru.practicum.exceptions;

import lombok.Data;

@Data
public class ErrorResponse {
    private final String status;
    private final String reason;
    private final String timestamp;
    private final String error;

    public ErrorResponse(String status, String reason, String error, String timestamp) {
        this.status = status;
        this.reason = reason;
        this.timestamp = timestamp;
        this.error = error;
    }
}
