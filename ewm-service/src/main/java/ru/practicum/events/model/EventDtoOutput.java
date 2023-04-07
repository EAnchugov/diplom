package ru.practicum.events.model;

import lombok.*;
import ru.practicum.variables.State;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EventDtoOutput {
    private Integer id;
    private String title;
    private String annotation;
    private Integer category;
    private Boolean paid;
    private String eventDate;
    private Integer initiator;
    private String description;
    private Integer participantLimit;
    private State state;
    private String createdOn;
    private Location location;
    private boolean requestModeration;
    private String publishedOn;
    private Long views;
    private Integer confirmedRequests;
}
