package ru.practicum.comments;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.comments.model.CommentDto;
import ru.practicum.comments.model.CommentDtoOutput;
import ru.practicum.comments.model.CommentUpdateDto;
import ru.practicum.comments.model.CommentsMapper;

@RequiredArgsConstructor
@RestController
@RequestMapping("/comments")
public class UserCommentsController {
    private final CommentService service;
    @GetMapping
    public String createCompilation() {
        return "testOK";
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/user/{userId}")
    public CommentDtoOutput createComment (@PathVariable  Integer userId,
                                           @RequestBody CommentDto commentDto) {
        return CommentsMapper.toOutput(service.create(CommentsMapper.toComment(commentDto, userId)));
    }
    @PatchMapping ("/user/{userId}")
    public CommentDtoOutput update (@PathVariable  Integer userId,
                                    @RequestBody CommentUpdateDto updateDto) {
        return CommentsMapper.toOutput(service.update(updateDto, userId));
    }

    @DeleteMapping("/{commentId}/user/{userId}")
    public void delete (@PathVariable  Integer commentId,
                        @PathVariable Integer userId) {
        service.deleteByUser(commentId,userId);
    }

}
