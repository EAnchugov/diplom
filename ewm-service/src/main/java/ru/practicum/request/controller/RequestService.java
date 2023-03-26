package ru.practicum.request.controller;

import ru.practicum.request.model.Request;

public interface RequestService {
    Request create(Integer userId, Integer eventId);
}
