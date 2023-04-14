package ru.practicum.request.model;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestDtoOutput {
    private Integer id;
    private Integer requester;
    private Integer event;
    private Status status;
    private LocalDateTime created;
}