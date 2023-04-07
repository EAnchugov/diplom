package ru.practicum.request.service;

import ru.practicum.events.model.Event;
import ru.practicum.request.model.Request;
import ru.practicum.request.model.RequestsUpdateDto;

import java.util.List;

public interface RequestService {
    Request create(Integer userId, Integer eventId);

    void approveRequest(Integer userId, Integer eventId);

    List<Request> getAllByEvent(Event event);

    List<Request> update(Integer userId, Integer eventId, RequestsUpdateDto updateDto);

    List<Request> getByUserId(Integer userId);
}
