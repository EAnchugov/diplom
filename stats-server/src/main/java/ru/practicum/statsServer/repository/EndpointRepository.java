package ru.practicum.statsServer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.practicum.statsServer.model.Endpoint;

import java.time.LocalDateTime;
import java.util.List;

public interface EndpointRepository extends JpaRepository<Endpoint, Long> {
    List<Endpoint> findAllByTimestampBetween(LocalDateTime start, LocalDateTime end);
    List<Endpoint> findDistinctByTimestampBetween(LocalDateTime start, LocalDateTime end);
    List<Endpoint> findDistinctByTimestampBetweenAndUriIn(LocalDateTime start, LocalDateTime end, List<String> uris);
    List<Endpoint> findAllByTimestampBetweenAndUriIn(LocalDateTime start, LocalDateTime end, List<String> uris);
}

