package ru.practicum.request.model;

import ru.practicum.events.model.EventsMapper;
import ru.practicum.user.model.UserMapper;
import ru.practicum.variables.GlobalVariables;

public class RequestMapper {
    public static RequestDtoOutput toOutput(Request request) {
        return RequestDtoOutput.builder()
                .id(request.getId())
                .requester(UserMapper.toUserDto(request.getRequester()))
                .event(EventsMapper.eventToOutput(request.getEvent()))
                .status(request.getStatus())
                .created(request.getCreated().format(GlobalVariables.FORMAT)).build();
    }
}
