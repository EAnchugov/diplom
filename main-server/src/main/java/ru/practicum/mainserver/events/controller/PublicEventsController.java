package ru.practicum.mainserver.events.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.mainserver.events.model.Events;
import ru.practicum.mainserver.events.model.EventsFullDto;
import ru.practicum.mainserver.events.model.EventsMapper;
import ru.practicum.mainserver.events.service.EventsService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/events")
@RequiredArgsConstructor
public class PublicEventsController {
    private final EventsService service;
    @GetMapping
    public List<EventsFullDto> getEvents() {
        return service.getAll().stream().map(EventsMapper::toEventsFullDto)
                .collect(Collectors.toList());
    };

    @GetMapping("/{id}")
    public EventsFullDto getEventById(@PathVariable Integer id) {
        return EventsMapper.toEventsFullDto(service.getById(id));
    }
}
