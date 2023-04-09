package ru.practicum.compilations.service;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.compilations.model.Compilation;
import ru.practicum.compilations.model.CompilationDtoInput;
import ru.practicum.compilations.model.UpdateCompilationRequest;
import ru.practicum.compilations.repository.CompilationRepository;
import ru.practicum.events.service.EventsService;
import ru.practicum.exceptions.WrongParameterException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CompilationServiceImpl implements CompilationService {
    private final CompilationRepository repository;
    private final EventsService eventsService;

    @Override
    @Transactional
    public Compilation create(CompilationDtoInput input) {

        Compilation compilation =
                Compilation.builder()
                        .title(input.getTitle())
                        .pinned(input.getPinned())
                        .events(input.getEvents().stream().map(id -> eventsService.getById(id)).collect(Collectors.toList()))
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

    @Override
    public Compilation getById(Integer compId) {
        return repository.findById(compId).orElseThrow(() -> new WrongParameterException("Нет компиляции с id " + compId));
    }

    @Override
    public void deleteById(Integer compId) {
        repository.deleteById(compId);
    }

    @Override
    @Transactional
    public Compilation update(Integer compId, UpdateCompilationRequest input) {
        System.out.println(input);
        Compilation compilation = getById(compId);
        if (!input.getEvents().isEmpty() && !(input.getEvents() == null)) {
            compilation.getEvents().addAll(input.getEvents().stream().map(id -> eventsService.getById(id)).collect(Collectors.toList()));
        }
        if (input.getPinned() != null) {
            input.getPinned();
        }
        if (input.getTitle() != null) {
            compilation.setTitle(input.getTitle());
        }
        return compilation;
    }
}
