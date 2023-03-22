package ru.practicum.compilations.service;

import ru.practicum.compilations.model.Compilation;

import java.util.List;

public interface CompilationService {
    Compilation create(Compilation compilation);

    List<Compilation> getAll(Boolean pinned, Integer from, Integer size);
}
