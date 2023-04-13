package ru.practicum.statsServer.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.practicum.statsServer.model.Endpoint;
import ru.practicum.statsServer.model.EndpointMapper;
import ru.practicum.statsServer.model.GlobalVariables;
import ru.practicum.statsServer.model.dto.EndpointDto;
import ru.practicum.statsServer.model.dto.EndpointDtoOutput;
import ru.practicum.statsServer.repository.CustomEndpointRepository;
import ru.practicum.statsServer.repository.EndpointRepository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class EndpointServiceImpl implements EndpointService {
    private final EndpointRepository repository;
    private final CustomEndpointRepository customEndpointRepository;

    @Transactional
    @Override
    public Endpoint create(Endpoint endpoint) {
        return repository.save(endpoint);
    }

    @Override
    public List<EndpointDtoOutput> getStats(LocalDateTime start, LocalDateTime end, List<String> uris, Boolean unique) {
        if (uris == null) {
            if (unique) {
                return repository.findDistinctByTimestampBetween(start,end);
            } else {
                return repository.findAllByTimestampBetween(start,end);
            }
        } else {
            if (unique) {
                hitFromGet(uris);
                List<EndpointDtoOutput> response = repository.findDistinctByTimestampBetweenAndUriIn(start,end,uris);
                return response;
            } else {
                List<EndpointDtoOutput> response = repository.findAllByTimestampBetweenAndUriIn(start,end,uris);
                hitFromGet(uris);
                return response;
//                return customEndpointRepository.findUniqueWithDateAndUri(uris,start,end).stream().map(EndpointMapper::toEndpointDtoOutput).collect(Collectors.toList());
            }
        }
    }

    private void hitFromGet(List<String> uri) {
        for (String u: uri) {
            create(EndpointMapper.toEndpoint(
                    EndpointDto.builder()
                            .app("stats-server")
                            .ip("127.0.0.1")
                            .timestamp(LocalDateTime.now().format(GlobalVariables.FORMAT))
                            .uri(u)
                            .build()
            ));
        }
    }
}
