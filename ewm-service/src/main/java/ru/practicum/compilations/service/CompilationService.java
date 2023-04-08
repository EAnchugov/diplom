package ru.practicum.compilations.service;

import ru.practicum.compilations.model.Compilation;
import ru.practicum.compilations.model.CompilationDto;
import ru.practicum.compilations.model.CompilationDtoInput;

import java.util.List;

public interface CompilationService {
    Compilation create(CompilationDtoInput compilation);

    List<Compilation> getAll(Boolean pinned, Integer from, Integer size);

    Compilation getById(Integer compId);

    void deleteById(Integer compId);

    Compilation update(Integer compId, CompilationDto input);
}
