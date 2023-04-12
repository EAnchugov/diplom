package ru.practicum.events.model;

import lombok.*;
import ru.practicum.categories.model.Category;
import ru.practicum.user.model.User;
import ru.practicum.variables.State;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table (name = "events")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Event {
    @Column(name = "annotation")
    private String annotation;
    @ManyToOne
    @JoinColumn (name = "category_id",nullable = false)
    private Category category;
    @Column(name = "created_on")
    private LocalDateTime createdOn;
    private String description;
    @Column(name = "eventdate")
    private LocalDateTime eventDate;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "initiator_id")
    private User initiator;
    private float lat;
    private float lon;
    private Boolean paid;
    @Column(name = "participantlimit")
    private Integer participantLimit;
    @Column(name = "publishedon")
    private LocalDateTime publishedOn;
    @Column(name = "requestmoderation")
    private Boolean requestModeration;
    @Enumerated(EnumType.STRING)
    private State state;
    private String title;

}
