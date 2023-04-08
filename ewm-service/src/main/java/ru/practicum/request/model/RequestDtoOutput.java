package ru.practicum.request.model;

import lombok.*;

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
    private String created;
}