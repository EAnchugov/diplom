package ru.practicum.events.model;

import lombok.AllArgsConstructor;
import ru.practicum.categories.model.Category;
import ru.practicum.user.model.User;
@AllArgsConstructor
public class EventDtoOutput {

    private Integer id;
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
}
