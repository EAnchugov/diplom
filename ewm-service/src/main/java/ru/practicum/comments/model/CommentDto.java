package ru.practicum.comments.model;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentDto{
    Integer id;
    @NotNull
    private Integer event;
    @Size(min = 1,max = 100, message = "Не может быть пустым и длиннее 100 символов")
    private String header;
    @Size(min = 1,max = 1000, message = "Не может быть пустым и длиннее 1000 символов")
    private String comment;
}
