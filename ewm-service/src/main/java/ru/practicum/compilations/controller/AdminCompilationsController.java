package ru.practicum.compilations.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.compilations.model.Compilation;
import ru.practicum.compilations.model.CompilationDto;
import ru.practicum.compilations.model.CompilationDtoInput;
import ru.practicum.compilations.model.CompilationMapper;
import ru.practicum.compilations.service.CompilationService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/admin/compilations")
public class AdminCompilationsController {
    private final CompilationService service;

    @PostMapping
    public CompilationDto addCompilation(@RequestBody @Validated CompilationDtoInput input) {
        Compilation compilation = service.create(CompilationMapper.toCompilation(input));
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
