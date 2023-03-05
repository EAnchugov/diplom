package ru.practicum.statsServer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.practicum.statsServer.model.Endpoint;

import java.time.LocalDateTime;
import java.util.List;

public interface EndpointRepository extends JpaRepository<Endpoint, Long> {

    @Query("SELECT ep from Endpoint ep where timestamp between :start and :end group by ep.app, ep.uri")
    List<Endpoint> findAllByTimestampBetween(LocalDateTime start, LocalDateTime end);

    @Query("SELECT ep from Endpoint ep where ep.uri in :uris and timestamp between :start and :end " +
            "group by ep.app, ep.uri, ep.ip")
    List<Endpoint> findAllByTimestampBetweenAndUriIn(LocalDateTime start, LocalDateTime end, List<String> uris);


    @Query("SELECT ep from Endpoint ep where timestamp between :start and :end group by ep.app, ep.uri")
    List<Endpoint> findUniqueByTimestampBetween(LocalDateTime start, LocalDateTime end);

    @Query("SELECT ep from Endpoint ep where ep.uri in :uris and timestamp between :start and :end" +
            " group by ep.app, ep.uri, ep.ip")
    List<Endpoint> findUniqueByTimestampBetweenAndUriIn(LocalDateTime start, LocalDateTime end, List<String> uris);
}

