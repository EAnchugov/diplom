package ru.practicum.statsServer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.practicum.statsServer.model.Endpoint;
import ru.practicum.statsServer.model.dto.EndpointDto;

import java.time.LocalDateTime;
import java.util.List;

public interface EndpointRepository extends JpaRepository<Endpoint, Long> {
    @Query("SELECT ep from Endpoint ep where timestamp between :start and :end")
    List<Endpoint> getStatsWithoutUri(LocalDateTime start, LocalDateTime end);
    List<Endpoint> findAllByTimestampBetween(LocalDateTime start, LocalDateTime end);
    List<Endpoint> findAllByTimestampBetweenAndUriIn(LocalDateTime start, LocalDateTime end, List<String> uris);
}

