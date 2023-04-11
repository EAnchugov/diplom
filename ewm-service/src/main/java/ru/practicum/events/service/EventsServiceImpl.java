package ru.practicum.events.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.categories.model.Category;
import ru.practicum.categories.service.user.UserCategoryService;
import ru.practicum.events.model.*;
import ru.practicum.events.repository.EventsRepository;
import ru.practicum.exceptions.WrongParameterException;
import ru.practicum.http.EndpointDto;
import ru.practicum.http.EndpointDtoOutput;
import ru.practicum.http.StatsClient;
import ru.practicum.user.model.User;
import ru.practicum.user.service.AdminUserService;
import ru.practicum.variables.GlobalVariables;
import ru.practicum.variables.Sorting;
import ru.practicum.variables.State;
import ru.practicum.variables.StateAction;

import javax.servlet.http.HttpServletRequest;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EventsServiceImpl implements EventsService {
    private final AdminUserService userService;
    private final EventsRepository repository;
    private final UserCategoryService categoryService;
    private final StatsClient statsClient;

    @Transactional
    @Override
    public EventDtoOutput createEvent(EventDtoInput eventDtoCreate, Integer userId) {
        if (LocalDateTime.parse(URLDecoder.decode(eventDtoCreate.getEventDate(), StandardCharsets.UTF_8),
                GlobalVariables.FORMAT).isBefore(LocalDateTime.now().plusHours(2L))) {
            throw new WrongParameterException("Дата и время на которые намечено событие не может быть раньше, " +
                    "чем через два часа от текущего момента");
        }
        Category category = categoryService.getByID(eventDtoCreate.getCategory());
        Event event = EventsMapper.inputToEvent(eventDtoCreate,category);
        event.setInitiator(userService.getById(userId));
        event.setState(State.PENDING);
        event.setPublishedOn(LocalDateTime.now());
        return EventsMapper.eventToOutput(repository.save(event));
    }

    @Override
    public List<Event> getUserEvents(Integer userId, Integer from, Integer size) {
        Pageable pageable = PageRequest.of(from, size);
        return repository.findAllByInitiatorId(userId,pageable);
    }

    @Override
    public List<Event> getUserEvent(Integer userId, Integer eventId) {
        User user = userService.getById(userId);
        return repository.findAllByIdAndInitiator(eventId,user);
    }

    @Override
    public Event updateEvent(Integer userId, Integer eventId, UpdateEventUserRequest updateEventUserRequest) {
        Event event = repository.findById(eventId)
                .orElseThrow(() -> new IllegalArgumentException("Не найден евент с таким ID"));
            return repository.save(eventUpdater(event, updateEventUserRequest));
    }

    @Override
    public Event adminEventUpdate(Integer eventId, UpdateEventUserRequest updateEventUserRequest) {
        Event event = repository.findById(eventId)
                .orElseThrow(() -> new IllegalArgumentException("Не найден евент с таким ID"));
        return repository.save(eventUpdater(event, updateEventUserRequest));
    }

    @Override
    public List<Event> getAll(String text,
                              Integer categoryId,
                              Boolean paid,
                              String rangeStart,
                              String rangeEnd,
                              Boolean onlyAvailable,
                              Sorting sorting,
                              Integer from,
                              Integer size, HttpServletRequest request) {
        Pageable pageable = PageRequest.of(from, size);
        LocalDateTime start;
        LocalDateTime end;
        if (rangeStart == null) {
            start = LocalDateTime.now();
        } else {
            start = LocalDateTime.parse(URLDecoder.decode(rangeStart, StandardCharsets.UTF_8),
                    GlobalVariables.FORMAT);
        }
        if (rangeEnd == null) {
            end = LocalDateTime.now().plusYears(100L);
        } else {
            end = LocalDateTime.parse(URLDecoder.decode(rangeEnd, StandardCharsets.UTF_8),
                GlobalVariables.FORMAT);
        }
        List<Event> events = repository.findAllWithFilter(text,categoryId,paid,start,end,State.PUBLISHED,pageable);
        return events;
    }

    @Override
    @Transactional
    public EventDtoOutput getByIdWithCount(Integer id, HttpServletRequest request) {
        ArrayList<String> uris = new ArrayList<>();
        String uri = request.getRequestURI();
        EndpointDto endpointDto = new EndpointDto(GlobalVariables.APP, uri, request.getRemoteAddr(),
                LocalDateTime.now().format(GlobalVariables.FORMAT));
        statsClient.hit(endpointDto);
        uris.add(uri);
        List<EndpointDtoOutput> hits = statsClient.get(LocalDateTime.now().minusYears(20L).format(GlobalVariables.FORMAT),
                LocalDateTime.now().plusYears(20L).format(GlobalVariables.FORMAT),
                uri,
                true);
//        Long sum = hits.stream().map(x -> x.getHits()).reduce(0L, Long::sum);
        EventDtoOutput eventDtoOutput = EventsMapper.eventToOutput(getById(id));
        eventDtoOutput.setViews(hits.get(0).getHits());
        return eventDtoOutput;
    }

    @Override
    public Event getById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Нет евента с нужным ID"));
    }

    @Override
    public List<Event> getFilteredEvents(Integer users,
                                         String states,
                                         Integer categories,
                                         String rangeStart,
                                         String rangeEnd,
                                         Integer from,
                                         Integer size) {
        LocalDateTime start;
        LocalDateTime end;
        State state;
        Pageable pageable = PageRequest.of(from, size);
        if (rangeStart == null) {
            start = LocalDateTime.now();
        } else {
             start = LocalDateTime.parse(URLDecoder.decode(rangeStart, StandardCharsets.UTF_8),
                    GlobalVariables.FORMAT);
        }
        if (rangeEnd == null) {
            end = LocalDateTime.now().plusYears(100L);
        } else {
            end = LocalDateTime.parse(URLDecoder.decode(rangeEnd, StandardCharsets.UTF_8),
                    GlobalVariables.FORMAT);
        }
        if (states == null) {
            return  repository.testMethod2(users,categories,start,end,pageable);

        } else {
            state = State.valueOf(states);
            return repository.testMethod(users,state,categories,start,end,pageable);
        }

    }

    private Event eventUpdater(Event event, UpdateEventUserRequest update) {
        if (update.getAnnotation() != null) {
         event.setAnnotation(update.getAnnotation());
        }
        if (update.getCategoryId() != null) {
            event.setCategory(categoryService.getByID(update.getCategoryId()));
        }
        if (update.getDescription() != null) {
            event.setDescription(update.getDescription());
        }
        if (update.getEventDate() != null) {
            LocalDateTime newEventDate = LocalDateTime.parse(URLDecoder.decode(update.getEventDate(),
                    StandardCharsets.UTF_8), GlobalVariables.FORMAT);
            if (newEventDate.isAfter(event.getCreatedOn().plusHours(1)) && event.getState().equals(State.PENDING)) {
                event.setEventDate(newEventDate);
            } else {
                throw new WrongParameterException(
                        "    дата начала события должна быть не ранее чем за час от даты публикации.\n" +
                        "    событие должно быть в состоянии ожидания публикации\n");
            }
        }
        if (update.getLocation() != null) {
            event.setLat(update.getLocation().getLat());
            event.setLon(update.getLocation().getLon());
        }
        if (update.getPaid() != null) {
            event.setPaid(update.getPaid());
        }
        if (update.getParticipantLimit() != null) {
            event.setParticipantLimit(update.getParticipantLimit());
        }
        if (update.getRequestModeration() != null) {
            event.setRequestModeration(update.getRequestModeration());
        }
        if (update.getStateAction().equals(StateAction.CANCEL_REVIEW)) {
            if (event.getState().equals(State.PENDING)) {
                event.setState(State.CANCELED);
            } else {
                throw new WrongParameterException("Отменить можно только событие в состоянии ожидания модерации.");
            }
        }
        if (update.getStateAction().equals(StateAction.SEND_TO_REVIEW)) {
            event.setState(State.PENDING);
        }
        if (update.getStateAction().equals(StateAction.PUBLISH_EVENT)) {
            if (event.getState().equals(State.PUBLISHED)) {
                throw new WrongParameterException("Эвент уже опубликован");
            }
            if (event.getState().equals(State.CANCELED)) {
                throw new WrongParameterException("Эвент отменен");
            }
            event.setState(State.PUBLISHED);
            event.setPublishedOn(LocalDateTime.now());
        }

        if (update.getStateAction().equals(StateAction.REJECT_EVENT)) {
            if (event.getState().equals(State.PUBLISHED)) {
                throw new WrongParameterException("Эвент уже опубликован");
            }
            event.setState(State.CANCELED);
        }

        if (update.getTitle()  != null) {
            event.setTitle(update.getTitle());
        }
        return event;
    }

    @Override
    public List<Event> getByCategoryId(Integer id) {
        return repository.findAllByCategoryId(id);
    }
}
