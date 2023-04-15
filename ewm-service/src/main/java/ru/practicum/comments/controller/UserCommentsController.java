package ru.practicum.comments.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.comments.service.CommentService;
import ru.practicum.comments.model.CommentDto;
import ru.practicum.comments.model.CommentDtoOutput;
import ru.practicum.comments.model.CommentUpdateDto;
import ru.practicum.comments.model.CommentsMapper;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/comments")
public class UserCommentsController {
    private final CommentService service;

    @GetMapping
    @ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
    public String createCompilation() {
        return "testOK";
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/user/{userId}")
    public CommentDtoOutput createComment(@PathVariable  Integer userId,
                                           @RequestBody CommentDto commentDto) {
        log.info("Создание комментария" + commentDto);
        return CommentsMapper.toOutput(service.create(CommentsMapper.toComment(commentDto, userId)));
    }

    @PatchMapping ("/user/{userId}")
    public CommentDtoOutput update(@PathVariable  Integer userId,
                                    @RequestBody CommentUpdateDto updateDto) {
        log.info("апдейт комментария" + updateDto.toString());
        return CommentsMapper.toOutput(service.update(updateDto, userId));
    }

    @DeleteMapping("/{commentId}/user/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable  Integer commentId,
                        @PathVariable Integer userId) {
        service.deleteByUser(commentId,userId);
    }
}
