package ru.practicum.request.model;

import lombok.*;
import ru.practicum.events.model.EventDtoOutput;
import ru.practicum.user.model.UserDto;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestDtoOutput {
    private Integer id;
    private UserDto requester;
    private EventDtoOutput event;
    private Status status;
    private String created;
}