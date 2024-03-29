package ru.practicum.compilations.service;

import ru.practicum.compilations.model.Compilation;
import ru.practicum.compilations.model.CompilationDtoInput;
import ru.practicum.compilations.model.UpdateCompilationRequest;

import java.util.List;

public interface CompilationService {
    Compilation create(CompilationDtoInput compilation);

    List<Compilation> getAll(Boolean pinned, Integer from, Integer size);

    Compilation getById(Integer compId);

    void deleteById(Integer compId);

    Compilation update(Integer compId, UpdateCompilationRequest input);
}
