package ru.practicum.events.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.events.model.*;
import ru.practicum.events.service.EventsService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class PrivateEvensController {
    private final EventsService service;

    @GetMapping("/{userId}/events")
    public List<EventsFullDto> getUserEvents(@PathVariable Integer userId) {
        return service.getUserEvents(userId).stream().map(EventsMapper::toEventsFullDto).collect(Collectors.toList());
    }

//    http://localhost:8080/users/31/requests?eventId=0
//    @PostMapping("/{userId}/events")
//    public EventsFullDto addUserEvents(@PathVariable Integer userId,
//                                @RequestBody EventsFullDto eventsDto) {
//               Event events = service.create(EventsMapper.toEvents(eventsDto),userId);
//        return EventsMapper.toEventsFullDto(events);
//    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{userId}/events")
    public EventDtoOutput addUserEvents(@PathVariable Integer userId,
                                       @RequestBody EventDtoInput eventDtoCreate) {
        return service.createEvent(eventDtoCreate,userId);
    }

    @GetMapping("/{userId}/events/{eventId}")
    public List<EventsFullDto> getUserEvent(@PathVariable Integer userId,
                                @PathVariable Integer eventId) {
        return service.getUserEvent(userId,eventId).stream().map(EventsMapper::toEventsFullDto)
                .collect(Collectors.toList());
    }

    @PatchMapping("/{userId}/events/{eventId}")
    public Event patchUserEvent(@PathVariable Integer userId,
                                @PathVariable Integer eventId,
                                @RequestBody UpdateEventUserRequest updateEventUserRequest) {
        return service.updateEvent(userId, eventId, updateEventUserRequest);
    }

    @GetMapping("/{userId}/events/{eventId}/requests")
    public String getUserEventRequest(@PathVariable Integer userId,
                                      @PathVariable Integer eventId) {
        return null;
    }

    @PatchMapping("/{userId}/events/{eventId}/requests")
    public String patchUserEventRequest(@PathVariable Integer userId,
                                      @PathVariable Integer eventId) {
        return null;
    }
}
