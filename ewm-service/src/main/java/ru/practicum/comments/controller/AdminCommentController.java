package ru.practicum.comments.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.comments.model.Comment;
import ru.practicum.comments.service.CommentService;
import ru.practicum.comments.model.CommentDtoOutput;
import ru.practicum.comments.model.CommentUpdateDto;
import ru.practicum.comments.model.CommentsMapper;

@RequiredArgsConstructor
@RestController
@RequestMapping("/comments/admin")
public class AdminCommentController {
    private final CommentService service;

    @PatchMapping("/{adminId}")
    public CommentDtoOutput update(@RequestBody CommentUpdateDto update,
                                   @PathVariable Integer adminId) {
        Comment comment = service.adminUpdate(update, adminId);

        return CommentsMapper.toOutput(comment);
    }

    @DeleteMapping("/{commentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer commentId) {
        service.adminDelete(commentId);
    }


}
