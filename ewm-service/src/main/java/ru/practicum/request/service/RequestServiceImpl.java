package ru.practicum.request.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.events.model.Event;
import ru.practicum.events.service.EventsService;
import ru.practicum.exceptions.WrongParameterException;
import ru.practicum.request.controller.RequestRepository;
import ru.practicum.request.model.Request;
import ru.practicum.request.model.RequestsUpdateDto;
import ru.practicum.request.model.Status;
import ru.practicum.user.model.User;
import ru.practicum.user.service.AdminUserService;
import ru.practicum.variables.State;

import java.time.LocalDateTime;
import java.util.List;

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
        User requester = userService.getById(userId);
        if (repository.findAllByRequesterAndEvent(requester,event).isPresent()) {
            throw new WrongParameterException("Нельзя подавать повторый запрос на участие");
        }
        if (event.getInitiator().getId().equals(userId)) {
            throw new WrongParameterException("Нельзя подавать запрос на участие в своем событии");
        }
        if (!event.getState().equals(State.PUBLISHED)) {
            throw new WrongParameterException("Нельзя подавать запрос на участие в неопубликованном событии");
        }
        if (event.getParticipantLimit() <= repository.findAllByEvent(event).size()) {
            throw new WrongParameterException("Лимит события уже достигнут");
        }

        Request newRequest = Request.builder()
                .requester(requester)
                .event(event)
                .status(Status.PENDING)
                .created(LocalDateTime.now()).build();
        return repository.save(newRequest);
    }

    @Override
    public void approveRequest(Integer userId, Integer eventId) {
        Event event = eventsService.getById(eventId);
        User requester = userService.getById(userId);
        if (event.getParticipantLimit() <= getAllByEvent(event).size()) {
            throw new WrongParameterException("Лимит события уже достигнут");
        }
    }

    @Override
    public List<Request> getAllByEvent(Event event) {
        return repository.findAllByEvent(event);

    }

    @Override
    @Transactional
    public List<Request> update(Integer userId, Integer eventId, RequestsUpdateDto updateDto) {
        if (updateDto == null) {
            throw new WrongParameterException("Список обновляемых событий пуст");
        }
        List<Request> requests = repository.findAllById(updateDto.getRequestIds());
        for (Request r: requests) {
            if (r.getStatus().equals(Status.CONFIRMED)) {
                throw new WrongParameterException("Нельзя изменять уже принятую заявку");
            }
            r.setStatus(updateDto.getStatus());
        }
        return requests;
    }

    @Override
    public List<Request> getByUserId(Integer userId) {
        return repository.findAllByRequesterId(userId);
    }

    @Override
    @Transactional
    public List<Request> getEventsWithUserRequest(Integer userId, Integer eventId) {
        return repository.findAllByEventInitiatorIdAndEventId(userId,eventId);
    }
}
