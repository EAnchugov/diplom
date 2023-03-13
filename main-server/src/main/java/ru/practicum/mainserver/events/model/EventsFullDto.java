package ru.practicum.mainserver.events.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import ru.practicum.mainserver.categories.model.CategoryDto;
import ru.practicum.mainserver.user.dto.UserShortDto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
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