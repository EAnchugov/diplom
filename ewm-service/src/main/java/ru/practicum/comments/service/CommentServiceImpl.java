package ru.practicum.comments.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.comments.model.Comment;
import ru.practicum.comments.model.CommentState;
import ru.practicum.comments.model.CommentUpdateDto;
import ru.practicum.comments.repository.CommentRepository;
import ru.practicum.exceptions.WrongParameterException;
import ru.practicum.user.model.User;
import ru.practicum.user.service.AdminUserService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository repository;
    private final AdminUserService userService;

    @Override
    @Transactional
    public Comment create(Comment comment) {
        User author = userService.getById(comment.getAuthor());
        comment.setModified(CommentState.ORIGINAL);
        return repository.save(comment);
    }

    @Override
    @Transactional
    public Comment update(CommentUpdateDto update, Integer userId) {
        Comment comment = getById(update.getId());
        if (!Objects.equals(comment.getAuthor(), userId)) {
            throw new WrongParameterException("Изменять комментарий может только автор");
        }
        return repository.save(commentUpdater(update));
    }

    @Override
    @Transactional
    public void deleteByUser(Integer commentId, Integer userId) {
        Comment comment = getById(commentId);
        if (comment.getAuthor() == userId) {
            repository.delete(comment);
        } else {
            throw new WrongParameterException("Удалять может только автор комментария");
        }
    }

    @Override
    @Transactional
    public Comment adminUpdate(CommentUpdateDto update, Integer adminId) {
        Comment comment = getById(update.getId());
        if (!update.getHeader().isEmpty()) {
            comment.setHeader(update.getHeader());
        }
        if (!update.getComment().isEmpty()) {
            comment.setComment(update.getComment());
        }
        comment.setTimestamp(LocalDateTime.now());
        comment.setModified(CommentState.MODIFIED);
        return comment;
    }

    private Comment commentUpdater(CommentUpdateDto update) {
        Comment comment = getById(update.getId());
        if (!update.getHeader().isEmpty()) {
            comment.setHeader(update.getHeader());
        }
        if (!update.getComment().isEmpty()) {
            comment.setComment(update.getComment());
        }
        comment.setTimestamp(LocalDateTime.now());
        comment.setModified(CommentState.MODIFIED);
        return comment;
    }

    @Override
    @Transactional
    public void adminDelete(Integer commentId) {
        Comment comment = getById(commentId);
        repository.delete(comment);
    }

    @Override
    @Transactional
    public List<Comment> getByCompilations(Integer id) {
        return repository.findAllByEventId(id);
    }

    private Comment getById(Integer id) {
        return repository.findById(id).orElseThrow(()
                -> new IllegalArgumentException("Не найден комментарий с таким ID"));
    }
}
