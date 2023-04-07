package ru.practicum.request.model;

import ru.practicum.variables.GlobalVariables;

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
