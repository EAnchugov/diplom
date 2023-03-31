package ru.practicum.events.model;

import ru.practicum.request.model.Status;

import java.util.List;

public class EventRequestStatusUpdateRequest {
    List<Integer> ids;
    Status status;
}
