package ru.practicum.compilations.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
public class CompilationDto {
    @NotBlank
    private  Integer id;
    @NotBlank
    private Boolean pinned;
    @NotBlank
    private String title;
}
