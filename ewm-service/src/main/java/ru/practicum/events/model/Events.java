package ru.practicum.events.model;

import lombok.*;
import ru.practicum.user.model.User;

import javax.persistence.*;
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
   @ManyToOne
    private User initiator;
    private float lat;
    private float lon;
    private Boolean paid;
    private Integer participantLimit;
    private LocalDateTime publishedOn;
    private Boolean requestModeration;
    private State state;
    private String title;
    private Integer views;
}
