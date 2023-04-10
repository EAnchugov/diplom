package ru.practicum.statsServer.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.practicum.statsServer.model.Endpoint;
import ru.practicum.statsServer.model.dto.EndpointDtoOutput;
import ru.practicum.statsServer.repository.EndpointRepository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class EndpointServiceImpl implements EndpointService {
    private final EndpointRepository repository;

    @Transactional
    @Override
    public Endpoint create(Endpoint endpoint) {
        return repository.save(endpoint);
    }

    @Override
    public List<EndpointDtoOutput> getStats(LocalDateTime start, LocalDateTime end, List<String> uris, Boolean unique) {
//        List<EndpointDtoOutput> stats;
//        if (uris.isEmpty()) {
//            if (unique.equals(true)) {
//                stats =
//            } else {
//                stats =
//            }
//        } else {
//            if (unique.equals(true)) {
//
//            } else {
//                stats =
//            }
//        }
////        return stats;


        if (uris == null) {
            if (unique) {
                return repository.findDistinctByTimestampBetween(start,end);
            } else {
                return repository.findAllByTimestampBetween(start,end);
            }
        } else {
            if (unique) {
                return repository.findDistinctByTimestampBetweenAndUriIn(start,end,uris);
            } else {
               return repository.findAllByTimestampBetweenAndUriIn(start,end,uris);
            }
        }



    }
}
