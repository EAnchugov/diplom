package ru.practicum.request.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class RequestDtoOutput {
    private LocalDateTime created;
    private Integer event;
    private Integer id;
    private Integer requesterId;
    private  Status status;
}
