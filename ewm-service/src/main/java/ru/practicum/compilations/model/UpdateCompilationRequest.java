package ru.practicum.compilations.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCompilationRequest {
    private List<Integer> events;
    private Boolean pinned;
    private String title;
}
