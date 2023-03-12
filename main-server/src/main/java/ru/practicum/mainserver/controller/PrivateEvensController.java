package ru.practicum.mainserver.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.practicum.mainserver.model.EventsFullDto;
import ru.practicum.mainserver.service.events.EventsService;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class PrivateEvensController {
    private final EventsService service;

    @GetMapping("/{userId}/events")
    public String getUserEvents(@PathVariable Integer userId) {
        return null;
    }

    @PostMapping("/{userId}/events")
    public String addUserEvents(@PathVariable Integer userId,
                                @RequestBody EventsFullDto eventsDto) {
        return null;
    }

    @GetMapping("/{userId}/events/{eventId}")
    public String getUserEvents(@PathVariable Integer userId,
                                @PathVariable Integer eventId) {
        return null;
    }

    @PatchMapping("/{userId}/events/{eventId}")
    public String patchUserEvent(@PathVariable Integer userId,
                                 @PathVariable Integer eventId) {
        return null;
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
