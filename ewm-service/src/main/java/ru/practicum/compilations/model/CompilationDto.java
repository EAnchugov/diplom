package ru.practicum.compilations.model;

import com.sun.istack.NotNull;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompilationDto {
    @NotNull
    private  Integer id;
    @NotBlank
    private Boolean pinned;
    @NotBlank
    private String title;
    private List<Integer> events;
}
