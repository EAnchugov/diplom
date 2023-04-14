package ru.practicum.comments.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.practicum.comments.service.CommentService;
import ru.practicum.comments.model.CommentDtoOutput;
import ru.practicum.comments.model.CommentUpdateDto;
import ru.practicum.comments.model.CommentsMapper;

@RequiredArgsConstructor
@RestController
@RequestMapping("/comments/admin")
public class AdminCommentController {
    private final CommentService service;

    @PatchMapping
    public CommentDtoOutput update(@RequestBody CommentUpdateDto update) {
        return CommentsMapper.toOutput(service.adminUpdate(update));
    }

    @DeleteMapping("/{commentId}")
    public void delete(@PathVariable Integer commentId) {
        service.adminDelete(commentId);
    }


}
