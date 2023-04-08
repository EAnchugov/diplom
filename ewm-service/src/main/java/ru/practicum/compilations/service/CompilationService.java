package ru.practicum.compilations.service;

import ru.practicum.compilations.model.Compilation;
import ru.practicum.compilations.model.CompilationDtoInput;

import java.util.List;

public interface CompilationService {
    Compilation create(CompilationDtoInput compilation);

    List<Compilation> getAll(Boolean pinned, Integer from, Integer size);
}
