package ru.practicum.request.controller;

import ru.practicum.request.model.Request;

public interface RequestController {
    Request create(Integer userId, Integer eventId);
}
