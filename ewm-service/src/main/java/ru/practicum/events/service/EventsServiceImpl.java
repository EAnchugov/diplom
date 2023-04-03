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
import ru.practicum.user.model.User;
import ru.practicum.user.service.AdminUserService;
import ru.practicum.variables.GlobalVariables;
import ru.practicum.variables.Sorting;
import ru.practicum.variables.State;
import ru.practicum.variables.StateAction;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EventsServiceImpl implements EventsService {
    private final AdminUserService userService;
    private final EventsRepository repository;
    private final UserCategoryService categoryService;

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
                              Integer category,
                              Boolean paid,
                              String rangeStart,
                              String rangeEnd,
                              Boolean onlyAvailable,
                              Sorting sorting,
                              Integer from,
                              Integer size) {
        Pageable pageable = PageRequest.of(from, size);
        LocalDateTime start;
        LocalDateTime end;
        State state = State.PUBLISHED;







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
        List<Event> events = repository.findAllWithFilter(text,category,paid,start,end,State.PUBLISHED,pageable);
        return events;
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
//        State state = states;
        Pageable pageable = PageRequest.of(from, size);
        LocalDateTime start = LocalDateTime.parse(URLDecoder.decode(rangeStart, StandardCharsets.UTF_8),
                    GlobalVariables.FORMAT);
        LocalDateTime end = LocalDateTime.parse(URLDecoder.decode(rangeEnd, StandardCharsets.UTF_8),
                    GlobalVariables.FORMAT);
        State state = State.valueOf(states);
//        User initiator = userService.getById(users);
//        Category category = categoryService.getByID(categories);

//        System.out.println(users + " " +states + " " +categories + " " +start + " " +end + " " +from + " " +size);


//        return repository.findAllByInitiatorAndStateAndCategoryAndEventDateIsBeforeAndEventDateIsAfter(
//                initiator,state,category,start,end,pageable);

        return repository.testMethod(users,state,categories,start,end,pageable);
    }

//    @Override
//    @Transactional
//    public List<Event> getAdminEvents(List<Integer> usersIds,
//                                      List<State> states,
//                                      List<Integer> categories,
//                                      String rangeStart,
//                                      String rangeEnd,
//                                      Integer from,
//                                      Integer size) {
//        Pageable pageable = PageRequest.of(from, size);
//        LocalDateTime start;
//        LocalDateTime end;
//        if (rangeStart == null) {
//            start = LocalDateTime.now();
//        } else {
//            start = LocalDateTime.parse(URLDecoder.decode(rangeStart, StandardCharsets.UTF_8),
//                    GlobalVariables.FORMAT);
//        }
//
//        if (rangeEnd == null) {
//            end = LocalDateTime.now();
//        } else {
//            end = LocalDateTime.parse(URLDecoder.decode(rangeEnd, StandardCharsets.UTF_8),
//                    GlobalVariables.FORMAT);
//        }
//
//        List<User> users = new ArrayList<>();
//        if (usersIds == null) {
//            users.addAll(userService.getAllUsers());
//        } else {
//            users.addAll(usersIds.stream().map(userService::getById).collect(Collectors.toList()));
//        }
//
//        if (states == null || states.isEmpty()) {
//            states.add(State.PUBLISHED);
//            states.add(State.PENDING);
//            states.add(State.CANCELED);
//        }
//
//        List<Category> currentCategories = new ArrayList<>();
//        if (categories == null) {
//            currentCategories = categoryService.getAllCategories();
//        } else {
//            currentCategories.addAll(categories.stream().map(categoryService::getByID).collect(Collectors.toList()));
//        }
//
//        return repository.findAllByEventDateIsBeforeAndEventDateIsAfterAndInitiatorInAndStateInAndCategoryIn(
////                start,end,users,states,currentCategories,pageable);
//        return new ArrayList<>();
//    }

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
