package ru.practicum.events.model;

import ru.practicum.categories.model.Category;
import ru.practicum.variables.GlobalVariables;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

public class EventsMapper {
    public static EventsFullDto toEventsFullDto(Event events) {
        return EventsFullDto.builder()
                .annotation(events.getAnnotation())
                .createdOn(events.getCreatedOn().toString())
                .description(events.getDescription())
                .build();
    }

    public static Event inputToEvent(EventDtoInput input, Category category) {
        return Event.builder()
                .annotation(input.getAnnotation())
                .category(category)
                .description(input.getDescription())
                .eventDate(LocalDateTime.parse(URLDecoder.decode(input.getEventDate(), StandardCharsets.UTF_8),
                        GlobalVariables.FORMAT))
                .lat(input.getLocation().getLat())
                .lon(input.getLocation().getLon())
                .paid(input.getPaid())
                .participantLimit(input.getParticipantLimit())
                .requestModeration(input.getRequestModeration())
                .title(input.getTitle())
                .build();
    }

    public static EventDtoOutput eventToOutput(Event event) {
        return new EventDtoOutput(event.getId(),
                event.getTitle(),
                event.getAnnotation(),
                event.getCategory(),
                event.getPaid(),
                event.getEventDate().format(GlobalVariables.FORMAT),
                event.getInitiator(),
                event.getDescription(),
                event.getParticipantLimit(),
                event.getState(),
                event.getCreatedOn().format(GlobalVariables.FORMAT),
                new Location(event.getLat(), event.getLon()),
                event.getRequestModeration());
    }


}
