package ru.practicum.mainserver.model.Compilation;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@Builder
public class CompilationDto {
 //   events	[...]
    @NotBlank
    private  Integer id;
    @NotBlank
    private Boolean pinned;
    @Size(min = 1, max = 100, message = "Поле не может быть пустым, максимальный размер 100 символов")
    private String title;
}
