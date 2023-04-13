package ru.practicum.statsServer.repository;

import org.springframework.stereotype.Repository;
import ru.practicum.statsServer.model.Endpoint;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CustomEndpointRepositoryImpl implements CustomEndpointRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Endpoint> findUniqueWithDateAndUri(List<String> uris, LocalDateTime start, LocalDateTime end) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Endpoint> query = cb.createQuery(Endpoint.class);
        Root<Endpoint> endpointRoot = query.from(Endpoint.class);
        Path<String> endpointPath = endpointRoot.get("uri");
        List<Predicate> predicates = new ArrayList<>();
        for (String uri : uris) {
            predicates.add(cb.like(endpointPath, uri));
        }
        query.select(endpointRoot)
                .where(cb.or(predicates.toArray(new Predicate[predicates.size()])));

        return entityManager.createQuery(query)
                .getResultList();
    }

}
