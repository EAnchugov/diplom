package ru.practicum.request.controller;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.practicum.events.model.Event;
import ru.practicum.request.model.Request;
import ru.practicum.user.model.User;

import java.util.List;
import java.util.Optional;

public interface RequestRepository extends JpaRepository<Request, Integer> {
    Optional<Request> findAllByRequesterAndEvent(User requester, Event event);

    List<Request> findAllByEvent(Event event);

    List<Request> findAllByRequesterId(Integer requesterId);

    List<Request> findAllByEventInitiatorIdAndEventId(Integer userId, Integer eventId);

    List<Request> findAllByIdIn(List<Integer> ids);

    @Query("select r from Request r join Event e on e.id = r.event.id " +
            "where e.id = :eventId and e.initiator.id = :userId and r.id in :ids")
    List<Request> getSelectedRequest(Integer userId, Integer eventId, List<Integer> ids);

    @Query("select r from Request r join Event e on e.id = r.event.id " +
            "where e.id = :eventId and e.initiator.id = :userId and r.id not in :ids")
    List<Request> getRemainingRequest(Integer userId, Integer eventId, List<Integer> ids);
}
