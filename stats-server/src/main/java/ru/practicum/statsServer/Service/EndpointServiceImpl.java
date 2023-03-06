package ru.practicum.statsServer.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.practicum.statsServer.model.Endpoint;
import ru.practicum.statsServer.repository.EndpointRepository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
    public List<Endpoint> getStats(LocalDateTime start, LocalDateTime end, List<String> uris, Boolean unique) {
//        System.out.println("Все" + repository.findAll());
//        //без ури уникальные
//        System.out.println("без ури уникальные" + repository.findDistinctByTimestampBetween(start,end));
//        //без ури все
//        System.out.println("без ури все" + repository.findAllByTimestampBetween(start,end));
//        //с ури уникальные
//        System.out.println("с ури уникальные" + repository.findDistinctByTimestampBetweenAndUriIn(start,end,uris));
//        //с ури все
//        System.out.println("с ури все" + repository.findAllByTimestampBetweenAndUriIn(start,end,uris));
//
//
//        return null;
        if (uris.isEmpty()) {
            if (unique.equals(true)) {
                return  repository.findDistinctByTimestampBetween(start,end);
            } else {
                return repository.findAllByTimestampBetween(start,end);
            }
        } else {
            if (unique.equals(true)) {
                return repository.findDistinctByTimestampBetweenAndUriIn(start,end,uris);
            } else {
                return repository.findAllByTimestampBetweenAndUriIn(start,end,uris);

            }
        }
    }
}
