package ru.practicum.compilations.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.compilations.model.Compilation;
import ru.practicum.compilations.repository.CompilationRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class CompilationServiceImpl implements CompilationService {
    private final CompilationRepository repository;

    @Override
    public Compilation create(Compilation compilation) {
        return null;
    }

    @Override
    public List<Compilation> getAll(Boolean pinned, Integer from, Integer size) {
        return repository.findAll();
    }
}
