package ru.practicum.comments.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CommentDtoOutput {
    private Integer id;
    private Integer author;
    private Integer event;
    private String header;
    private String comment;
    private String timestamp;
    private CommentState modified;
}
