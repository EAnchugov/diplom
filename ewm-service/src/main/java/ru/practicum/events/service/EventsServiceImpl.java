package ru.practicum.events.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.categories.model.Category;
import ru.practicum.categories.service.user.UserCategoryService;
import ru.practicum.events.model.*;
import ru.practicum.events.repository.EventsRepository;
import ru.practicum.user.model.User;
import ru.practicum.user.service.AdminUserService;
import ru.practicum.variables.Sorting;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EventsServiceImpl implements EventsService {
    private final AdminUserService userService;
    private final EventsRepository repository;
    private final UserCategoryService categoryService;

    @Override
    public EventDtoOutput createEvent(EventDtoInput eventDtoCreate, Integer userId) {
        Category category = categoryService.getByID(eventDtoCreate.getCategory());
        Event event = EventsMapper.inputToEvent(eventDtoCreate,category);
        event.setInitiator(userService.getById(userId));
        return EventsMapper.eventToOutput(repository.save(event));
    }



    @Override
    public List<Event> getUserEvents(Integer userId) {
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
    public List<Event> getUserEvent(Integer userId, Integer eventId) {
        User user = userService.getById(userId);
        return repository.findAllByIdAndInitiator(eventId,user);
    }

    @Override
    public Event updateEvent(Integer userId, Integer eventId, UpdateEventUserRequest updateEventUserRequest) {
        Event event = repository.findById(eventId)
                .orElseThrow(() -> new IllegalArgumentException("Не найден евент с таким ID"));
        if (!event.getInitiator().getId().equals(userId)) {
            throw new IllegalArgumentException("Вы не автор эвента");
        } else {
            event = eventUpdater(event, updateEventUserRequest);
            return repository.save(event);
        }
    }

    @Override
    public List<Event> getAll(String text,
                              List<Integer> categories,
                              Boolean paid,
                              String rangeStart,
                              String rangeEnd,
                              Boolean onlyAvailable,
                              Sorting sorting,
                              Integer from,
                              Integer size) {
        List<Event> events = new ArrayList<>();
     //   events.addAll(repository.findAll());
        return events;
    }

    @Override
    public Event getById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Нет евента с нужным ID"));
    }

    @Override
    public List<Event> getAdminEvents(List<Integer> users,
                                      List<State> states,
                                      List<Integer> categories,
                                      List<String> starts,
                                      Integer from,
                                      Integer size) {
        return new ArrayList<>();
    }



    private Event eventUpdater(Event event, UpdateEventUserRequest update) {
//
//        if (!update.getAnnotation().equals(null)) {
//         event.setAnnotation(update.getAnnotation());
//        }
//        if (!update.getCategoryId().equals(null)) {
//            event.setCategory(update.getCategoryId());
//        }
//        if (!update.getDescription().equals(null)) {
//            event.setDescription(update.getDescription());
//        }
//        if (!update.getEventDate().equals(null)) {
//            LocalDateTime newEventDate = LocalDateTime.parse(URLDecoder.decode(update.getEventDate(),
//                    StandardCharsets.UTF_8), GlobalVariables.FORMAT);
//            event.setEventDate(newEventDate);
//        }
//        if (!update.getLocation().equals(null)) {
//            event.setLat(update.getLocation().getLat());
//            event.setLon(update.getLocation().getLon());
//        }
//        if (!update.getPaid().equals(null)) {
//            event.setPaid(update.getPaid());
//        }
//        if (!update.getParticipantLimit().equals(null)) {
//            event.setParticipantLimit(update.getParticipantLimit());
//        }
//        if (!update.getRequestModeration().equals(null)) {
//            event.setRequestModeration(update.getRequestModeration());
//        }
//        if (!update.getStateAction().equals(null)) {
//            event.setState(update.getStateAction());
//        }
//        if (!update.getTitle().equals(null)) {
//            event.setTitle(update.getTitle());
//        }
        return event;
    }
}
