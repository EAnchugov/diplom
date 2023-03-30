package ru.practicum.request.service;

import ru.practicum.request.model.Request;

public interface RequestService {
    Request create(Integer userId, Integer eventId);
}
