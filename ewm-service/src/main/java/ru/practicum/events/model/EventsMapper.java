package ru.practicum.events.model;

import ru.practicum.variables.GlobalVariables;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

public class EventsMapper {
    public static Events toEvents(EventsFullDto dto) {
        return Events.builder()
                .id(dto.getId())
                .annotation(dto.getAnnotation())
                .category(dto.getCategory().getId())
                .confirmedRequests(dto.getConfirmedRequests())
                .createdOn(LocalDateTime.parse(URLDecoder.decode(dto.getCreatedOn(), StandardCharsets.UTF_8), GlobalVariables.FORMAT))
                .description(dto.getDescription())
                .eventDate(LocalDateTime.parse(URLDecoder.decode(dto.getEventDate(), StandardCharsets.UTF_8), GlobalVariables.FORMAT))
  //              .initiator(dto.getInitiator())
                .paid(dto.getPaid())
                .participantLimit(dto.getParticipantLimit())
                .publishedOn(LocalDateTime.parse(URLDecoder.decode(dto.getPublishedOn(), StandardCharsets.UTF_8), GlobalVariables.FORMAT))
                .requestModeration(dto.getRequestModeration())
                .title(dto.getTitle())
                .views(dto.getViews())
                .lat(dto.getLocation().getLat())
                .lon(dto.getLocation().getLon())
                .state(dto.getState())
                .build();
    }

    public static EventsFullDto toEventsFullDto(Events events) {
        return EventsFullDto.builder().build();
    }
}
