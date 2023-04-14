package ru.practicum.comments.model;

import lombok.*;


import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@Entity
@Table(name = "comments")
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer author;
    private Integer eventId;
    private String header;
    private String comment;
    private LocalDateTime timestamp;
    private CommentState modified;
}
