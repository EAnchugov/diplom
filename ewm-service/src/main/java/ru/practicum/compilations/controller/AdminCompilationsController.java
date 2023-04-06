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
    public Compilation addCompilation(@RequestBody @Validated CompilationDtoInput input) {
//        Compilation compilation = service.create(Compilation.builder()
//                        .title(input.getTitle())
//                        .pinned(input.getPinned())
//                        .build());
//
//        return compilation;
        return null;
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
