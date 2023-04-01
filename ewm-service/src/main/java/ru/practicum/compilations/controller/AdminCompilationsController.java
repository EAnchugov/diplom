package ru.practicum.compilations.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.compilations.model.*;
import ru.practicum.compilations.service.CompilationService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/admin/compilations")
public class AdminCompilationsController {
    private final CompilationService service;

    @PostMapping
    public CompilationDtoOutput addCompilation(@RequestBody @Validated CompilationDtoInput input) {
        Compilation compilation = service.create(CompilationMapper.toCompilation(input));

        return CompilationMapper.toOutput(compilation);
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
