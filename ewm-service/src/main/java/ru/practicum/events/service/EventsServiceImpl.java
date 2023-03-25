package ru.practicum.events.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.events.model.EventDtoCreate;
import ru.practicum.events.model.Events;
import ru.practicum.events.model.State;
import ru.practicum.events.model.UpdateEventUserRequest;
import ru.practicum.events.repository.EventsRepository;
import ru.practicum.user.model.User;
import ru.practicum.user.service.AdminUserService;
import ru.practicum.variables.GlobalVariables;
import ru.practicum.variables.Sorting;

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

    @Override
    public Events create(Events events, Integer userId) {
        User user = userService.getById(userId);
        events.setInitiator(user);
        return repository.save(events);
    }

    @Override
    public List<Events> getUserEvents(Integer userId) {
        return new ArrayList<>();
//        User user = userService.getById(userId)
//        List<Events> events = new ArrayList<>();
//        events.add(repository.getAllByInitiator(user)) ;
////        User user = userService.getById(userId);
////        events = repository.getAllByInitiator(user);
////        events.addAll();
//        return events;
    }

    @Override
    public List<Events> getUserEvent(Integer userId, Integer eventId) {
        User user = userService.getById(userId);
        return repository.findAllByIdAndInitiator(eventId,user);
    }

    @Override
    public Events updateEvent(Integer userId, Integer eventId, UpdateEventUserRequest updateEventUserRequest) {
        Events event = repository.findById(eventId)
                .orElseThrow(() -> new IllegalArgumentException("Не найден евент с таким ID"));
        if (!event.getInitiator().getId().equals(userId)) {
            throw new IllegalArgumentException("Вы не автор эвента");
        } else {
            event = eventUpdater(event, updateEventUserRequest);
            return repository.save(event);
        }
    }

    @Override
    public List<Events> getAll(String text,
                               List<Integer> categories,
                               Boolean paid,
                               String rangeStart,
                               String rangeEnd,
                               Boolean onlyAvailable,
                               Sorting sorting,
                               Integer from,
                               Integer size) {
        List<Events> events = new ArrayList<>();
     //   events.addAll(repository.findAll());
        return events;
    }

    @Override
    public Events getById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Нет евента с нужным ID"));
    }

    @Override
    public List<Events> getAdminEvents(List<Integer> users,
                                       List<State> states,
                                       List<Integer> categories,
                                       List<String> starts,
                                       Integer from,
                                       Integer size) {
        return new ArrayList<>();
    }

    @Override
    public EventDtoCreate testCreateevent(Integer userId, EventDtoCreate eventDtoCreate) {
        return eventDtoCreate;
    }

    private Events eventUpdater(Events event, UpdateEventUserRequest update) {

        if (!update.getAnnotation().equals(null)) {
         event.setAnnotation(update.getAnnotation());
        }
        if (!update.getCategoryId().equals(null)) {
            event.setCategory(update.getCategoryId());
        }
        if (!update.getDescription().equals(null)) {
            event.setDescription(update.getDescription());
        }
        if (!update.getEventDate().equals(null)) {
            LocalDateTime newEventDate = LocalDateTime.parse(URLDecoder.decode(update.getEventDate(),
                    StandardCharsets.UTF_8), GlobalVariables.FORMAT);
            event.setEventDate(newEventDate);
        }
        if (!update.getLocation().equals(null)) {
            event.setLat(update.getLocation().getLat());
            event.setLon(update.getLocation().getLon());
        }
        if (!update.getPaid().equals(null)) {
            event.setPaid(update.getPaid());
        }
        if (!update.getParticipantLimit().equals(null)) {
            event.setParticipantLimit(update.getParticipantLimit());
        }
        if (!update.getRequestModeration().equals(null)) {
            event.setRequestModeration(update.getRequestModeration());
        }
        if (!update.getStateAction().equals(null)) {
            event.setState(update.getStateAction());
        }
        if (!update.getTitle().equals(null)) {
            event.setTitle(update.getTitle());
        }
        return event;
    }
}
