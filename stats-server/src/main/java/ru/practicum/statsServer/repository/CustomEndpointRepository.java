package ru.practicum.statsServer.repository;

import ru.practicum.statsServer.model.Endpoint;

import java.time.LocalDateTime;
import java.util.List;

public interface CustomEndpointRepository {
    List<Endpoint> findUniqueWithDateAndUri(List<String> uris, LocalDateTime start, LocalDateTime end);
}
