package ru.practicum.request.model;

public class RequestMapper {
    public static RequestDtoOutput toOutput(Request request) {
        return RequestDtoOutput.builder()
                .created(request.getCreated())
                .event(request.getEvent().getId())
                .id(request.getId())
                .requesterId(request.getRequester().getId())
                .status(request.getStatus()).build();

    }
}
