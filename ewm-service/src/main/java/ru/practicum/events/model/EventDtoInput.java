package ru.practicum.events.model;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EventDtoInput {
    @NotBlank
    private String annotation;
    @NotBlank
    private Integer category;
    @NotBlank
    private String description;
    @NotBlank
    private String eventDate;
    @NotBlank
    private Location location;
    private Boolean paid;
    private Integer participantLimit;
    private Boolean requestModeration;
    @NotBlank
    private String title;
}
