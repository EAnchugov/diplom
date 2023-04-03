package ru.practicum.events.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.events.model.Event;
import ru.practicum.events.model.EventDtoOutput;
import ru.practicum.events.model.EventsMapper;
import ru.practicum.events.model.UpdateEventUserRequest;
import ru.practicum.events.service.EventsService;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@RestController
@RequestMapping("/admin/events")
@RequiredArgsConstructor
public class AdminEventsController {

    private final EventsService eventsService;

    @GetMapping
    public List<Event> getFilteredEvents(@RequestParam(required = false) Integer users,
                                 @RequestParam(required = false) String states,
                                 @RequestParam(required = false) Integer categories,
                                 @RequestParam(required = false) String rangeStart,
                                 @RequestParam(required = false) String rangeEnd,
                                 @Positive @RequestParam(defaultValue = "0") Integer from,
                                 @PositiveOrZero @RequestParam(defaultValue = "10") Integer size
    ) {
        return eventsService.getFilteredEvents(users,states,categories,rangeStart,rangeEnd,from,size);
    }

    @PatchMapping("/{eventId}")
    public EventDtoOutput adminEventUpdate(@Positive @PathVariable Integer eventId,
                                           @RequestBody @Validated UpdateEventUserRequest updateEventUserRequest) {
        return EventsMapper.eventToOutput(eventsService.adminEventUpdate(eventId,updateEventUserRequest));
    }
}
