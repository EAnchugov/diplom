package ru.practicum.events.service;

import ru.practicum.events.model.Event;
import ru.practicum.events.model.EventDtoInput;
import ru.practicum.events.model.EventDtoOutput;
import ru.practicum.events.model.UpdateEventUserRequest;
import ru.practicum.variables.Sorting;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface EventsService {

    List<Event> getUserEvents(Integer userId, Integer from, Integer size);

    List<Event> getAllByIds(List<Integer> events);

    List<Event> getUserEvent(Integer userId, Integer eventId);

    Event updateEvent(Integer userId, Integer eventId, UpdateEventUserRequest updateEventUserRequest);

    List<Event> getAll(String text, Integer categories, Boolean paid, String rangeStart, String rangeEnd, Boolean onlyAvailable, Sorting sorting, Integer from, Integer size, HttpServletRequest request);

    EventDtoOutput getByIdWithCount(Integer id, HttpServletRequest request);

    Event getById(Integer id);

    List<Event> getFilteredEvents(
            Integer users, String state, Integer categories,
            String rangeStart,String rangeEnd, Integer from, Integer size);

    EventDtoOutput createEvent(EventDtoInput eventDtoCreate, Integer userId);

    List<Event> getByCategoryId(Integer id);

    Event adminEventUpdate(Integer eventId, UpdateEventUserRequest updateEventUserRequest);
}
