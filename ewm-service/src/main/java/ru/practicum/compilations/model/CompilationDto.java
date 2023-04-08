package ru.practicum.compilations.model;

import lombok.*;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompilationDto {
    @NotBlank
    private  Integer id;
    @NotBlank
    private Boolean pinned;
    @NotBlank
    private String title;
    private List<Integer> events;
}
