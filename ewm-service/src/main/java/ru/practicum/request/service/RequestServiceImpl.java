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
    public EventRequestStatusUpdateResult update(Integer userId, Integer eventId, RequestsUpdateDto updateDto) {
        EventRequestStatusUpdateResult answer = new EventRequestStatusUpdateResult();
        List<Request> requests = repository.findAllById(updateDto.getRequestIds());
        for (Request r: requests) {
            //        если для события лимит заявок равен 0 или отключена пре-модерация заявок, то подтверждение заявок не требуется
            if (r.getEvent().getRequestModeration().equals(false) || (r.getEvent().getParticipantLimit() == 0)) {
                r.setStatus(Status.CONFIRMED);
                answer.getConfirmedRequests().add(RequestMapper.toOutput(r));
                break;
            }
            if (!r.getStatus().equals(Status.PENDING)) {
                throw new WrongParameterException("статус можно изменить только у заявок, находящихся в состоянии ожидания");
            }
            if (getAllByEvent(r.getEvent()).size() == (r.getEvent().getParticipantLimit())) {
                throw new WrongParameterException("Лимит события уже достигнут");
            }






//        если при подтверждении данной заявки, лимит заявок для события исчерпан, то все неподтверждённые заявки необходимо отклонить




        }

        return new EventRequestStatusUpdateResult();
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
