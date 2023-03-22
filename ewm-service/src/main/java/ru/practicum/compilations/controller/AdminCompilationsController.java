package ru.practicum.compilations.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.practicum.compilations.model.Compilation;
import ru.practicum.compilations.model.CompilationDto;
import ru.practicum.compilations.model.CompilationMapper;
import ru.practicum.compilations.service.CompilationService;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/admin/compilations")
public class AdminCompilationsController {
    private final CompilationService service;

    @PostMapping
    public CompilationDto addCompilation(@Valid @RequestBody CompilationDto compilationDto) {
        Compilation compilation = service.create(CompilationMapper.toCompilation(compilationDto));
        return CompilationMapper.toCompilationDto(compilation);
    }

    @DeleteMapping("/{compId}")
    public String deleteCompilation(@PathVariable Integer compId) {
        return null;
    }

    @PatchMapping("/{compId}")
    public String patchCompilation(@PathVariable Integer compId) {
        return null;
    }
}
