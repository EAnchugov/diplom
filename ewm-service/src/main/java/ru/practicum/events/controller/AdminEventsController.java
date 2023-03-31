package ru.practicum.events.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.practicum.events.model.Event;
import ru.practicum.events.model.EventDtoOutput;
import ru.practicum.variables.State;
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
    public List<Event> getEvents(@RequestParam(required = false) List<Integer> users,
                                 @RequestParam(required = false) List<State> states,
                                 @RequestParam(required = false) List<Integer> categories,
                                 @RequestParam(required = false) List<String> starts,
                                 @Positive @RequestParam(defaultValue = "0") Integer from,
                                 @PositiveOrZero @RequestParam(defaultValue = "10") Integer size
    ) {
        return eventsService.getAdminEvents(users,states,categories,starts,from,size);
    }

    @PatchMapping
    public EventDtoOutput adminEventUpdate() {
        EventDtoOutput eventDtoOutput = new EventDtoOutput();
        eventDtoOutput.setAnnotation("asdasda sdasdasdasd");
        return eventDtoOutput;
    }
}
