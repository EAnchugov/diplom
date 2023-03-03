package ru.practicum.statsServer.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.practicum.statsServer.model.Endpoint;
import ru.practicum.statsServer.repository.EndpointRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class EndpointServiceImpl implements EndpointService {
    private final EndpointRepository repository;

    @Override
    public Endpoint create(Endpoint endpoint) {
        return repository.save(endpoint);
    }

    @Override
    public List<Endpoint> getStats(LocalDateTime start, LocalDateTime end, List<String> uris, Boolean unique) {
        if (uris.isEmpty()) {
            if (unique.equals(true)) {
                repository.findAllByTimestampBetween(start,end);
            } else {
                return repository.findAllByTimestampBetweenAndUriIn(start,end,uris);
            }

        } else {
            if (unique.equals(true)) {
                repository.findAllByTimestampBetween(start,end);
            } else {
                repository.findAllByTimestampBetweenAndUriIn(start,end,uris);
            }
        }
        return null;
    }
}
