package ru.practicum.events.service;

import ru.practicum.events.model.Events;
import ru.practicum.events.model.State;
import ru.practicum.events.model.UpdateEventUserRequest;
import ru.practicum.variables.Sorting;

import java.util.List;

public interface EventsService {
    Events create(Events events, Integer userId);

    List<Events> getUserEvents(Integer userId);

    List<Events> getUserEvent(Integer userId, Integer eventId);

    Events updateEvent(Integer userId, Integer eventId, UpdateEventUserRequest updateEventUserRequest);

    List<Events> getAll(String text, List<Integer> categories, Boolean paid, String rangeStart, String rangeEnd, Boolean onlyAvailable, Sorting sorting, Integer from, Integer size);

    Events getById(Integer id);

    List<Events> getAdminEvents(
            List<Integer> users, List<State> states, List<Integer> categories,
            List<String> starts, Integer from, Integer size);
}
