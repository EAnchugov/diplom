package ru.practicum.events.model;

import lombok.*;
import ru.practicum.categories.model.Category;
import ru.practicum.user.model.User;
import ru.practicum.variables.State;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EventDtoOutput {
    private Integer eventId;
    private String title;
    private String annotation;
    private Category category;
    private Boolean paid;
    private String eventDate;
    private User initiator;
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
