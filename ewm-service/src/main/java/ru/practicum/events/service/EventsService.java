package ru.practicum.events.service;

import ru.practicum.events.model.Event;
import ru.practicum.events.model.EventDtoInput;
import ru.practicum.events.model.EventDtoOutput;
import ru.practicum.events.model.UpdateEventUserRequest;
import ru.practicum.variables.Sorting;

import java.util.List;

public interface EventsService {

    List<Event> getUserEvents(Integer userId, Integer from, Integer size);

    List<Event> getUserEvent(Integer userId, Integer eventId);

    Event updateEvent(Integer userId, Integer eventId, UpdateEventUserRequest updateEventUserRequest);

    List<Event> getAll(String text, List<Integer> categories, Boolean paid, String rangeStart, String rangeEnd, Boolean onlyAvailable, Sorting sorting, Integer from, Integer size);

    Event getById(Integer id);

    List<Event> getFilteredEvents(
            Integer users, String state, Integer categories,
            String rangeStart,String rangeEnd, Integer from, Integer size);

    EventDtoOutput createEvent(EventDtoInput eventDtoCreate, Integer userId);

    List<Event> getByCategoryId(Integer id);

    Event adminEventUpdate(Integer eventId, UpdateEventUserRequest updateEventUserRequest);
}
