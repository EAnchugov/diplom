package ru.practicum.mainserver.events.model;

import lombok.*;
import ru.practicum.mainserver.categories.model.CategoryDto;
import ru.practicum.mainserver.user.dto.UserShortDto;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table (name = "events")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Events {
    private String annotation;
    private Integer category;
    private Integer confirmedRequests;
    private LocalDateTime createdOn;
    private String description;
    private LocalDateTime eventDate;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private UserShortDto initiator;
    private Location location;
    private Boolean paid;
    private Integer participantLimit;
    private LocalDateTime publishedOn;
    private Boolean requestModeration;
    private State state;
    private String title;
    private Integer views;
}
