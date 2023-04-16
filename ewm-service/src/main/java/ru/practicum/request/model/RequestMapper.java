package ru.practicum.request.model;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import ru.practicum.variables.GlobalVariables;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RequestMapper {
    public static RequestDtoOutput toOutput(Request request) {
        return RequestDtoOutput.builder()
                .id(request.getId())
                .requester(request.getRequester().getId())
                .event(request.getEvent().getId())
                .status(request.getStatus())
                .created(request.getCreated().format(GlobalVariables.FORMAT)).build();

    }
}
