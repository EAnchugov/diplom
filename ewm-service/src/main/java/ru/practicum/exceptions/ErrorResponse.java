package ru.practicum.exceptions;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ErrorResponse {
    private final String status;
    private final String reason;
    private final String timestamp;
    private final String error;
}
