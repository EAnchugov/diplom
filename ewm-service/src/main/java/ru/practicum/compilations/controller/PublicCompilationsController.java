package ru.practicum.compilations.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.practicum.compilations.model.CompilationDto;
import ru.practicum.compilations.model.CompilationMapper;
import ru.practicum.compilations.service.CompilationService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/compilations")
@AllArgsConstructor
public class PublicCompilationsController {
    private final CompilationService compilationService;

    @GetMapping
    public List<CompilationDto> getAllCompilations(@RequestParam Boolean pinned,
                                                   @RequestParam Integer from,
                                                   @RequestParam Integer size) {
        return compilationService.getAll(pinned,from,size).stream().map(CompilationMapper::toCompilationDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{complId}")
    public String getCompilations(@PathVariable Integer complId) {
        return null;
    }
}
