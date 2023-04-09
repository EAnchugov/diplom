package ru.practicum.compilations.model;

import com.sun.istack.NotNull;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCompilationRequest {
    private List<Integer> events;
    @NotNull
    private  Integer id;
    private Boolean pinned;
    private String title;
}
