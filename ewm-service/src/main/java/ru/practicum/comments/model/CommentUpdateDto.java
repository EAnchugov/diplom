package ru.practicum.comments.model;

import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentUpdateDto {
    @NotNull
    private Integer id;
    private String header;
    private String comment;
}
