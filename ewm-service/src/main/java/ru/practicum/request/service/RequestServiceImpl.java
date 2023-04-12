package ru.practicum.request.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.events.model.Event;
import ru.practicum.events.service.EventsService;
import ru.practicum.exceptions.WrongParameterException;
import ru.practicum.request.controller.RequestRepository;
import ru.practicum.request.model.*;
import ru.practicum.user.model.User;
import ru.practicum.user.service.AdminUserService;
import ru.practicum.variables.State;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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
        if (event.getParticipantLimit() == repository.findAllByEvent(event).size()) {
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
    }

    @Override
    public List<Request> getAllByEvent(Event event) {
        return repository.findAllByEvent(event);

    }

    @Override
    @Transactional
    public EventRequestStatusUpdateResult update(Integer userId, Integer eventId, RequestsUpdateDto updateDto) {
        if (!(updateDto.getRequestIds().isEmpty())) {
            List<Request> requests = repository.findAllByIdIn(updateDto.getRequestIds());
            for (Request r: requests) {
                if (updateDto.getStatus().equals(Status.REJECTED)) {
                    r.setStatus(Status.REJECTED);
                }
            }
            EventRequestStatusUpdateResult result = new EventRequestStatusUpdateResult();
            for (Request r: requests) {
                if (updateDto.getStatus().equals(Status.REJECTED)) {
                    if (r.getStatus().equals(Status.CONFIRMED)) {
                        throw new WrongParameterException("статус можно изменить только у заявок, находящихся в состоянии ожидания");
                    }
                    result.getRejectedRequests().add(RequestMapper.toOutput(r));
                }
            }
            return result;
        }
        if (updateDto.getRequestIds().isEmpty()) {
            Event event = eventsService.getById(eventId);
            User requester = userService.getById(userId);
            if (event.getParticipantLimit() == repository.findAllByEvent(event).size()) {
                throw new WrongParameterException("Лимит события уже достигнут");
            }
        }
        return null;
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

    @Override
    public Request cancel(Integer userId, Integer requestId) {
        Optional<Request> optionalRequest = repository.findById(requestId);
        if (optionalRequest.isPresent()) {
            Request request = optionalRequest.get();
            request.setStatus(Status.CANCELED);
            return repository.save(request);
        } else {
            throw new WrongParameterException("нет реквеста с ID" + requestId);
        }
    }
}
