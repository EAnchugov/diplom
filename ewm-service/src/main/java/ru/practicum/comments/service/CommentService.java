package ru.practicum.comments.service;

import org.springframework.transaction.annotation.Transactional;
import ru.practicum.comments.model.Comment;
import ru.practicum.comments.model.CommentDto;
import ru.practicum.comments.model.CommentUpdateDto;
import ru.practicum.compilations.model.Compilation;

import java.util.List;

public interface CommentService {
    Comment create(CommentDto commentDto,Integer authorId);

    Comment update(CommentUpdateDto updateDto, Integer userId);

    void deleteByUser(Integer commentId, Integer userId);

    Comment adminUpdate(CommentUpdateDto updateDto, Integer adminId);

    void adminDelete(Integer commentId);

    @Transactional
    List<Comment> getByCompilations(Integer id);

    @Transactional
    List<Comment> getByCompilations(List<Compilation> ids);
}
