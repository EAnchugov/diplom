package ru.practicum.request.controller;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.events.model.Event;
import ru.practicum.request.model.Request;
import ru.practicum.user.model.User;

import java.util.Optional;

public interface RequestRepository extends JpaRepository<Request, Integer> {
    Optional<Request> findAllByRequesterAndEvent(User requester, Event event);
}
