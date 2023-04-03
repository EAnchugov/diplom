package ru.practicum.events.service;

import ru.practicum.events.model.*;
import ru.practicum.variables.Sorting;
import ru.practicum.variables.State;

import java.util.List;

public interface EventsService {

    List<Event> getUserEvents(Integer userId);

    List<Event> getUserEvent(Integer userId, Integer eventId);

    Event updateEvent(Integer userId, Integer eventId, UpdateEventUserRequest updateEventUserRequest);

    List<Event> getAll(String text, List<Integer> categories, Boolean paid, String rangeStart, String rangeEnd, Boolean onlyAvailable, Sorting sorting, Integer from, Integer size);

    Event getById(Integer id);

    List<Event> getFilteredEvents(
            Integer users, State states, Integer categories,
            String rangeStart,String rangeEnd, Integer from, Integer size);

    EventDtoOutput createEvent(EventDtoInput eventDtoCreate, Integer userId);

    List<Event> getByCategoryId(Integer id);

    Event adminEventUpdate(Integer eventId, UpdateEventUserRequest updateEventUserRequest);
}
