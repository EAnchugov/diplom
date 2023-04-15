package ru.practicum.comments.model;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import ru.practicum.variables.GlobalVariables;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CommentsMapper {
    public static CommentDtoOutput toOutput(Comment comment) {
        return CommentDtoOutput.builder()
                .author(comment.getAuthor())
                .id(comment.getId())
                .timestamp(comment.getTimestamp().format(GlobalVariables.FORMAT))
                .modified(comment.getModified())
                .comment(comment.getComment())
                .header(comment.getHeader())
                .event(comment.getEventId())
                .build();
    }

    public static Comment toComment(CommentDto dto, Integer authorId) {
        return Comment.builder()
                .id(dto.getId())
                .author(authorId)
                .eventId(dto.getEvent())
                .header(dto.getHeader())
                .comment(dto.getComment())
                .timestamp(LocalDateTime.now())
                .build();
    }
}
