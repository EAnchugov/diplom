package ru.practicum.compilations.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Builder
public class CompilationDtoOutput {
    private Integer id;
    private String title;
    private Boolean pinned;
}
