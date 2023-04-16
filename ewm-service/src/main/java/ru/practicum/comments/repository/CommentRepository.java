package ru.practicum.comments.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.comments.model.Comment;
import ru.practicum.compilations.model.Compilation;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
    List<Comment> findAllByCompilationIdOrderByTimestampDesc(Integer id);

    List<Comment> findAllByCompilationInOrderByTimestampDesc(List<Compilation> ids);


}
