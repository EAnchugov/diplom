package ru.practicum.compilations.service;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.practicum.compilations.model.Compilation;
import ru.practicum.compilations.repository.CompilationRepository;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
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
        List<Compilation> compilations = new ArrayList<>();
        Pageable pageable = PageRequest.of(from,size);
        if (!pinned) {
            compilations.addAll(repository.findAll(pageable).toList());
        } else {
            compilations.addAll(repository.findAllByPinned(pinned,pageable));
        }
        return compilations;
    }
}
