package ru.practicum.compilations.service;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.practicum.compilations.model.Compilation;
import ru.practicum.compilations.model.CompilationDtoInput;
import ru.practicum.compilations.repository.CompilationRepository;
import ru.practicum.events.service.EventsService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CompilationServiceImpl implements CompilationService {
    private final CompilationRepository repository;
    private final EventsService eventsService;

    @Override
    public Compilation create(CompilationDtoInput input) {
        Compilation compilation =
                Compilation.builder()
                        .title(input.getTitle())
                        .pinned(input.getPinned())
                        .events(input.getEvents().stream().map(eventsService::getById).collect(Collectors.toList()))
                        .build();
        return repository.save(compilation);
    }

    @Override
    public List<Compilation> getAll(Boolean pinned, Integer from, Integer size) {
        List<Compilation> compilations = new ArrayList<>();
        Pageable pageable = PageRequest.of(from,size);
            compilations.addAll(repository.findAll(pageable).toList());
        return compilations;
    }
}
