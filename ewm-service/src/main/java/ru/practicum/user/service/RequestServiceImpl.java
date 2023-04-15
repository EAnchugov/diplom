package ru.practicum.user.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.events.model.Event;
import ru.practicum.events.service.EventsService;
import ru.practicum.exceptions.CreateException;
import ru.practicum.exceptions.WrongParameterException;
import ru.practicum.request.controller.RequestRepository;
import ru.practicum.request.model.*;
import ru.practicum.user.model.User;
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
        if (userId == null || eventId == null) {
            throw new CreateException("Что-то пошло не так при создании реквеста");
        }
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
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void approveRequest(Integer userId, Integer eventId) {
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public List<Request> getAllByEvent(Event event) {
        return repository.findAllByEvent(event);
    }

    @Override
    @Transactional
    public EventRequestStatusUpdateResult update(Integer userId, Integer eventId, RequestsUpdateDto updateDto) {
        if (updateDto == null) {
                throw new WrongParameterException("костыль");
        } else {
            List<Request> requests = repository.findAllByIdIn(updateDto.getRequestIds());
            for (Request r:requests) {
                if (r.getStatus().equals(Status.CONFIRMED)) {
                    throw new WrongParameterException("Нельзя изменять заявку с таким статусом.");
                }
                r.setStatus(updateDto.getStatus());
            }
            return createUpdateResult(requests);
        }
    }

    private EventRequestStatusUpdateResult createUpdateResult(List<Request> requests) {
        EventRequestStatusUpdateResult result = new EventRequestStatusUpdateResult();
        for (Request r: requests) {
            if (r.getStatus().equals(Status.CONFIRMED)) {
                result.getConfirmedRequests().add(RequestMapper.toOutput(r));
            } else {
                result.getRejectedRequests().add(RequestMapper.toOutput(r));
            }
        }
        return result;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public List<Request> getByUserId(Integer userId) {
        return repository.findAllByRequesterId(userId);
    }

    @Override
    @Transactional
    public List<Request> getEventsWithUserRequest(Integer userId, Integer eventId) {
        return repository.findAllByEventInitiatorIdAndEventId(userId,eventId);
    }

    @Override
    @Transactional
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
