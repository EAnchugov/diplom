package ru.practicum.events.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.events.model.*;
import ru.practicum.events.service.EventsService;

import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserEvensController {
    private final EventsService service;

    @GetMapping("/{userId}/events")
    public List<Event> getUserEvents(@PathVariable Integer userId,
                                             @Min(0) @RequestParam(defaultValue = "0") Integer from,
                                             @Min(1) @RequestParam(defaultValue = "10") Integer size) {
        return service.getUserEvents(userId,from,size);
//        .stream().map(EventsMapper::toEventsFullDto).collect(Collectors.toList());
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
}
