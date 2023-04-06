package ru.practicum.events.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.practicum.events.model.Event;
import ru.practicum.events.model.EventsFullDto;
import ru.practicum.events.model.EventsMapper;
import ru.practicum.events.service.EventsService;
import ru.practicum.variables.Sorting;

import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequestMapping(path = "/events")
@RequiredArgsConstructor
public class PublicEventsController {
    private final EventsService service;

    @GetMapping
    public List<Event> getEvents(
            @RequestParam(required = false) String text,
            @RequestParam(required = false) Integer categories,
            @RequestParam(defaultValue = "false") Boolean paid,
            @RequestParam(required = false) String rangeStart,
            @RequestParam(required = false)String rangeEnd,
            @RequestParam(defaultValue = "false") Boolean onlyAvailable,
            @RequestParam(defaultValue = "VIEWS") Sorting sorting,
            @Min(0) @RequestParam(defaultValue = "0") Integer from,
            @Min(1) @RequestParam(defaultValue = "10") Integer size
    ) {
        return service.getAll(text,categories,paid,rangeStart,rangeEnd,onlyAvailable,sorting,from,size);
//                .stream().map(EventsMapper::toEventsFullDto)
//                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public EventsFullDto getEventById(@PathVariable Integer id) {
        return EventsMapper.toEventsFullDto(service.getById(id));
    }
}
