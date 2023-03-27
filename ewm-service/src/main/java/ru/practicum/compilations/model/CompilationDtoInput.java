package ru.practicum.compilations.model;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CompilationDtoInput {
    @NotBlank
    private String title;
    @NotNull
    private Boolean pinned;
    private List<Integer> events;
}
