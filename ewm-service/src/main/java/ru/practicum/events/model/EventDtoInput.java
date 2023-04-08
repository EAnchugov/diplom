package ru.practicum.events.model;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EventDtoInput {
    @NotBlank
    private String annotation;
    @NotNull
    private Integer category;
    @NotBlank
    private String description;
    @NotBlank
    private String eventDate;
    @NonNull
    private Location location;
    private Boolean paid;
    private Integer participantLimit;
    private Boolean requestModeration;
    @NotBlank
    private String title;
}
