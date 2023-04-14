package ru.practicum.compilations.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CompilationDtoOutput {
    private Integer id;
    private String title;
    private Boolean pinned;
}
