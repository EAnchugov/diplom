package ru.practicum.events.model;

import lombok.Getter;
import lombok.Setter;
import ru.practicum.variables.StateAction;

import javax.persistence.Enumerated;

@Getter
@Setter
public class UpdateEventUserRequest {
    private String annotation;
    private Integer categoryId;
    private String description;
    private String eventDate;
    private Location location;
    private Boolean paid;
    private Integer participantLimit;
    private Boolean requestModeration;
    @Enumerated
    private StateAction stateAction;
    private String title;
}
