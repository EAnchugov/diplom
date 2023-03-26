package ru.practicum.events.service;

import ru.practicum.events.model.*;
import ru.practicum.variables.Sorting;

import java.util.List;

public interface EventsService {
    Event create(Event events, Integer userId);

    List<Event> getUserEvents(Integer userId);

    List<Event> getUserEvent(Integer userId, Integer eventId);

    Event updateEvent(Integer userId, Integer eventId, UpdateEventUserRequest updateEventUserRequest);

    List<Event> getAll(String text, List<Integer> categories, Boolean paid, String rangeStart, String rangeEnd, Boolean onlyAvailable, Sorting sorting, Integer from, Integer size);

    Event getById(Integer id);

    List<Event> getAdminEvents(
            List<Integer> users, List<State> states, List<Integer> categories,
            List<String> starts, Integer from, Integer size);

    EventDtoInput testCreateevent(Integer userId, EventDtoInput eventDtoCreate);

    EventDtoOutput createEvent(EventDtoInput eventDtoCreate, Integer userId);
}
