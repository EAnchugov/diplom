package ru.practicum.compilations.model;

import lombok.*;
import ru.practicum.events.model.Event;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "compilations")
public class Compilation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer id;
    @Column(name = "pinned")
    private Boolean pinned;
    @Column(name = "title")
    private String title;
    @ManyToMany (cascade = { CascadeType.ALL })
    @JoinTable(
            name = "events_compilations",
            joinColumns = {@JoinColumn(name = "compilations_id")},
            inverseJoinColumns = {@JoinColumn(name = "events_id")}
    )
    private List<Event> events;
}
