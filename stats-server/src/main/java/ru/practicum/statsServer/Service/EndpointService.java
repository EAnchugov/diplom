package ru.practicum.statsServer.Service;

import ru.practicum.statsServer.model.Endpoint;

import java.time.LocalDateTime;
import java.util.List;

public interface EndpointService {
    public Endpoint create(Endpoint endpoint);

    List<Endpoint> getStats(LocalDateTime start, LocalDateTime end, List<String> uris, Boolean unique);
}
