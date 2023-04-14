package ru.practicum.request.model;

import lombok.*;
import ru.practicum.events.model.Event;
import ru.practicum.user.model.User;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "requests")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User requester;
    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;
    private Status status;
    private LocalDateTime created;

    @Override
    public String toString() {
        return "Request{" +
                "id=" + id +
                ", requester=" + requester +
                ", event=" + event +
                ", status=" + status +
                ", created=" + created +
                '}';
    }
}
