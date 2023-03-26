package ru.practicum.exceptions;

public class ErrorResponse {
    private final String status;
    private final String reason;
    private final String timestamp;
    private final String error;



    public ErrorResponse(String status, String reason, String timestamp, String error) {
        this.status = status;
        this.reason = reason;
        this.timestamp = timestamp;
        this.error = error;
    }


    public String getError() {
        return error;
    }
}
