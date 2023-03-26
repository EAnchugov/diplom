package ru.practicum.request.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.events.model.Event;
import ru.practicum.events.service.EventsService;
import ru.practicum.request.model.Request;
import ru.practicum.request.model.Status;
import ru.practicum.user.service.AdminUserService;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class RequestServiceImpl implements RequestService {
    private final RequestRepository repository;
    private final AdminUserService userService;
    private final EventsService eventsService;

    @Transactional
    @Override
    public Request create(Integer userId, Integer eventId) {
        Event event = eventsService.getById(eventId);
        Request newRequest = Request.builder()
                .requester(userService.getById(userId))
                .event(event)
                .status(Status.PENDING)
                .created(LocalDateTime.now()).build();
        return repository.save(newRequest);
    }
}
