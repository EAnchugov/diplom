package ru.practicum.events.controller;

import lombok.RequiredArgsConstructor;
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

//    @PostMapping("/{userId}/events")
//    public EventsFullDto addUserEvents(@PathVariable Integer userId,
//                                @RequestBody EventsFullDto eventsDto) {
//               Events events = service.create(EventsMapper.toEvents(eventsDto),userId);
//        return EventsMapper.toEventsFullDto(events);
//    }

    @PostMapping("/{userId}/events")
    public EventDtoCreate addUserEvents(@PathVariable Integer userId,
                                       @RequestBody EventDtoCreate eventDtoCreate) {
//        Events events = service.create(EventsMapper.toEvents(eventsDto),userId);
        return service.testCreateevent(userId,eventDtoCreate);
    }

    @GetMapping("/{userId}/events/{eventId}")
    public List<EventsFullDto> getUserEvent(@PathVariable Integer userId,
                                @PathVariable Integer eventId) {
        return service.getUserEvent(userId,eventId).stream().map(EventsMapper::toEventsFullDto)
                .collect(Collectors.toList());
    }

    @PatchMapping("/{userId}/events/{eventId}")
    public Events patchUserEvent(@PathVariable Integer userId,
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
