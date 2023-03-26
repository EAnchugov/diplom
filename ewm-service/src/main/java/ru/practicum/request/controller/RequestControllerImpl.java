package ru.practicum.request.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import ru.practicum.events.service.EventsService;
import ru.practicum.request.model.Request;
import ru.practicum.request.model.Status;
import ru.practicum.user.service.AdminUserService;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Controller
@AllArgsConstructor
public class RequestControllerImpl implements RequestController {
    private final RequestRepository repository;
    private final AdminUserService userService;
    private final EventsService eventsService;

    @Transactional
    @Override
    public Request create(Integer userId, Integer eventId) {
        Request newRequest = Request.builder()
                .requester(userService.getById(userId))
                .event(eventsService.getById(eventId))
                .status(Status.PENDING)
                .created(LocalDateTime.now()).build();

        return repository.save(newRequest);
    }
}
