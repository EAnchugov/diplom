package ru.practicum.compilations.service;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.compilations.model.Compilation;
import ru.practicum.compilations.model.CompilationDto;
import ru.practicum.compilations.model.CompilationDtoInput;
import ru.practicum.compilations.repository.CompilationRepository;
import ru.practicum.events.model.Event;
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

    @Override
    public Compilation getById(Integer compId) {
        return repository.findById(compId).orElseThrow(() -> new WrongParameterException("Нет компиляции с id " + compId));
    }

    @Override
    public void deleteById(Integer compId) {
        repository.deleteById(compId);
    }

    @Override
    public Compilation update(Integer compId, CompilationDto input) {
        Compilation compilation = getById(compId);
        if (!input.getEvents().isEmpty()) {
            List<Event> events = new ArrayList<>();
            for (Event e: compilation.getEvents()) {
                events.add(e);
            }
           events.addAll(input.getEvents().stream().map(eventsService::getById).collect(Collectors.toList()));
            compilation.setEvents(events);
        }
        compilation.setPinned(input.getPinned());
        compilation.setTitle(input.getTitle());
        return repository.save(compilation);
    }
}
