package ru.practicum.compilations.service;

import org.springframework.stereotype.Service;
import ru.practicum.compilations.model.Compilation;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompilationServiceImpl implements CompilationService {
    @Override
    public Compilation create(Compilation compilation) {
        return null;
    }

    @Override
    public List<Compilation> getAll(Boolean pinned, Integer from, Integer size) {
        List<Compilation> compilations = new ArrayList<>();
        return compilations;
    }
}
