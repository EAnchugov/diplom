package ru.practicum.mainserver.model;

import ru.practicum.mainserver.model.Category.CategoryDto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class EventsFullDto {
    @NotBlank
    private String annotation;
    @NotBlank
    private CategoryDto category;
    private Integer confirmedRequests;

    private String createdOn;
    private String description;
    @NotBlank
    private String eventDate;
    private Integer id;
    @NotBlank
    private UserShortDto initiator;
    @NotBlank
    private Location location;
    @NotBlank
    private Boolean paid;
    private Integer participantLimit;
    private String publishedOn;
    private Boolean requestModeration;
    private State state;
    private String title;
    @NotNull
    private Integer views;
}
