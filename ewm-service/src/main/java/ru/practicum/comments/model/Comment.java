package ru.practicum.comments.model;

import lombok.*;
import ru.practicum.compilations.model.Compilation;
import ru.practicum.user.model.User;


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
    @ManyToOne
    @JoinColumn(name = "author")
    private User author;
    @ManyToOne
    @JoinColumn(name = "compilation_id")
    private Compilation compilation;
    private String header;
    private String comment;
    private LocalDateTime timestamp;
    private CommentState modified;
}
