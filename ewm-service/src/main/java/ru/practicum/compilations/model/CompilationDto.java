package ru.practicum.compilations.model;

import com.sun.istack.NotNull;
import lombok.*;
import ru.practicum.events.model.Event;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompilationDto {
    private List<Event> events;
    @NotNull
    private  Integer id;
    @NotNull
    private Boolean pinned;
    @NotBlank
    private String title;
}
