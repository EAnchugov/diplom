package ru.practicum.mainserver.events.service;

import ru.practicum.mainserver.events.model.Events;
import ru.practicum.mainserver.events.model.UpdateEventUserRequest;

import java.util.List;

public interface EventsService {
    Events create(Events events, Integer userId);

    List<Events> getUserEvents(Integer userId);

    List<Events> getUserEvent(Integer userId, Integer eventId);

    Events updateEvent(Integer userId, Integer eventId, UpdateEventUserRequest updateEventUserRequest);
}
