package ru.practicum.statsServer.Service;

import ru.practicum.statsServer.model.Endpoint;
import ru.practicum.statsServer.model.dto.EndpointDtoOutput;

import java.time.LocalDateTime;
import java.util.List;

public interface EndpointService {
    Endpoint create(Endpoint endpoint);

    List<EndpointDtoOutput> getStats(LocalDateTime start, LocalDateTime end, List<String> uris, Boolean unique);
}
