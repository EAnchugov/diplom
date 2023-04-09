package ru.practicum.events.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.practicum.events.model.EventDtoOutput;
import ru.practicum.events.model.EventsMapper;
import ru.practicum.events.service.EventsService;
import ru.practicum.variables.Sorting;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/events")
@RequiredArgsConstructor
public class PublicEventsController {
    private final EventsService service;

    @GetMapping
    public List<EventDtoOutput> getEvents(
            @RequestParam(required = false) String text,
            @RequestParam(required = false) Integer categories,
            @RequestParam(defaultValue = "false") Boolean paid,
            @RequestParam(required = false) String rangeStart,
            @RequestParam(required = false)String rangeEnd,
            @RequestParam(defaultValue = "false") Boolean onlyAvailable,
            @RequestParam(defaultValue = "VIEWS") Sorting sorting,
            @Min(0) @RequestParam(defaultValue = "0") Integer from,
            @Min(1) @RequestParam(defaultValue = "10") Integer size,
            HttpServletRequest request
    ) {
        return service.getAll(text,categories,paid,rangeStart,rangeEnd,onlyAvailable,sorting,from,size,request).stream()
                .map(EventsMapper::eventToOutput)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public EventDtoOutput getEventById(@PathVariable Integer id,HttpServletRequest request) {
        return service.getByIdWithCount(id,request);
    }
}
