package ru.practicum.request.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.events.model.Event;
import ru.practicum.events.model.EventRequestStatusUpdateRequest;
import ru.practicum.events.service.EventsService;
import ru.practicum.exceptions.WrongParameterException;
import ru.practicum.request.controller.RequestRepository;
import ru.practicum.request.model.Request;
import ru.practicum.request.model.Status;
import ru.practicum.user.model.User;
import ru.practicum.user.service.AdminUserService;
import ru.practicum.variables.State;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
    public List<Request> update(Integer userId, Integer eventId, EventRequestStatusUpdateRequest updateRequest) {
//        Event event = eventsService.getById(eventId);
//        if (event.getParticipantLimit().equals(0) || event.getRequestModeration().equals(false)) {
//            throw new WrongParameterException("если для события лимит заявок равен 0 " +
//                    "или отключена пре-модерация заявок, то подтверждение заявок не требуется");
//        }
//        if (event.getParticipantLimit() <= getAllByEvent(event).size()) {
//            throw new WrongParameterException("Лимит события уже достигнут");
//        }
//
//        User requester = userService.getById(userId);
//        List<Request> requests = repository.findAllById(updateRequest.getRequests());
//        for (Request r : requests) {
//            if (!r.getStatus().equals(Status.PENDING)) {
//                throw new WrongParameterException("статус можно изменить только у заявок," +
//                        " находящихся в состоянии ожидания");
//            }
//
//        }
//        List<Request> returningRequests = new ArrayList<>();
//        return returningRequests;





//
//
//
//        User requester = userService.getById(userId);
//        Event event = eventsService.getById(eventId);
//
//        List<Request> requests = repository.findAllByIdInAndRequesterAndAndEvent(updateRequest.getRequestIds(), requester, event);
//        for (Request request: requests) {
//            if (!request.getStatus().equals(Status.PENDING)) {
//                throw new WrongParameterException("статус можно изменить только у заявок," +
//                        " находящихся в состоянии ожидания");
//            }
//        }
//        List<Integer> remainingRequests = requests.stream().map(Request::getId).collect(Collectors.toList());






//        List<Request> selectedRequestsNew = repository.updateRequests(requestDto.getRequestIds(),
//                statusFromRequest.name());
//        Status status = Status.CONFIRMED;
//        if (statusFromRequest.equals(status)) {
//            status = Status.REJECTED;
//        }
//        List<Request> remainingRequestsNew;
//        if (!remainingRequests.isEmpty()) {
//            remainingRequestsNew = repository.updateRequests(remainingRequests, status.name());
//        } else {
//            remainingRequestsNew = List.of();
//        }
//        if (statusFromRequest.equals(Status.CONFIRMED)) {
//            return RequestsForStatusDtoOutput
//                    .builder()
//                    .confirmedRequests(RequestMapper.toRequestDtoList(selectedRequestsNew))
//                    .rejectedRequests(RequestMapper.toRequestDtoList(remainingRequestsNew))
//                    .build();
//        } else {
//            return RequestsForStatusDtoOutput
//                    .builder()
//                    .confirmedRequests(RequestMapper.toRequestDtoList(remainingRequestsNew))
//                    .rejectedRequests(RequestMapper.toRequestDtoList(selectedRequestsNew))
//                    .build();
//        }
                List<Request> returningRequests = new ArrayList<>();
                Request request = new Request();
                request.setId(6666);
                returningRequests.add(request);
        return returningRequests;
//    }
    }
}
