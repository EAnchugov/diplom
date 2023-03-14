package ru.practicum.compilations.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Compilation {
    private  Integer id;
    private Boolean pinned;
    private String title;
}
