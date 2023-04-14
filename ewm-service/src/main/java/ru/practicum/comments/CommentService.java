package ru.practicum.comments;

import org.springframework.transaction.annotation.Transactional;
import ru.practicum.comments.model.Comment;
import ru.practicum.comments.model.CommentUpdateDto;

public interface CommentService {
    Comment create(Comment comment);

    Comment update(CommentUpdateDto updateDto, Integer userId);

    void deleteByUser(Integer commentId, Integer userId);

    Comment adminUpdate(CommentUpdateDto updateDto);

    @Transactional
    Comment commentUpdater(CommentUpdateDto update);

    void adminDelete(Integer commentId);
}
