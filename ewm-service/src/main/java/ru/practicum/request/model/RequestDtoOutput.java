package ru.practicum.request.model;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestDtoOutput {
    private LocalDateTime created;
    private Integer event;
    private Integer id;
    private Integer requester;
    private  Status status;
}
