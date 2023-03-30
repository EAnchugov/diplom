package ru.practicum.events.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.events.model.*;
import ru.practicum.events.service.EventsService;
import ru.practicum.request.service.RequestService;

import javax.validation.constraints.Positive;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class PrivateEvensController {
    private final EventsService service;
    private final RequestService requestService;

    @GetMapping("/{userId}/events")
    public List<EventsFullDto> getUserEvents(@PathVariable Integer userId) {
        return service.getUserEvents(userId).stream().map(EventsMapper::toEventsFullDto).collect(Collectors.toList());
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{userId}/events")
    public EventDtoOutput addUserEvents(@PathVariable @Positive Integer userId,
                                       @Validated @RequestBody EventDtoInput eventDtoCreate) {
        return service.createEvent(eventDtoCreate,userId);
    }

    @GetMapping("/{userId}/events/{eventId}")
    public List<EventsFullDto> getUserEvent(@PathVariable Integer userId,
                                @PathVariable Integer eventId) {
        return service.getUserEvent(userId,eventId).stream().map(EventsMapper::toEventsFullDto)
                .collect(Collectors.toList());
    }

    @PatchMapping("/{userId}/events/{eventId}")
    public EventDtoOutput patchUserEvent(@PathVariable @Positive Integer userId,
                                @PathVariable @Positive Integer eventId,
                                @RequestBody @Validated UpdateEventUserRequest updateEventUserRequest) {
        return EventsMapper.eventToOutput(service.updateEvent(userId, eventId, updateEventUserRequest));
    }

    @GetMapping("/{userId}/events/{eventId}/requests")
    public String getUserEventRequest(@PathVariable Integer userId,
                                      @PathVariable Integer eventId) {
        return null;
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/{userId}/events/{eventId}/requests")
    public EventDtoOutput patchUserEventRequest(@PathVariable Integer userId,
                                      @PathVariable Integer eventId) {
//        service.changeUserRequestStatus(userId,eventId);
        return new EventDtoOutput();
    }
}
