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

    List<Event> getAdminEvents(
            List<Integer> users, List<State> states, List<Integer> categories,
            List<String> starts, Integer from, Integer size);

    EventDtoOutput createEvent(EventDtoInput eventDtoCreate, Integer userId);

    void changeUserRequestStatus(Integer userId, Integer eventId);

    List<Event> getByCategoryId(Integer id);

    Event adminEventUpdate(Integer eventId, UpdateEventUserRequest updateEventUserRequest);
}
