package ru.practicum.compilations.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.compilations.model.Compilation;
import ru.practicum.compilations.model.CompilationDtoInput;
import ru.practicum.compilations.service.CompilationService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/admin/compilations")
public class AdminCompilationsController {
    private final CompilationService service;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Compilation createCompilation(@RequestBody @Validated CompilationDtoInput input) {
        Compilation compilation = service.create(input);
        return compilation;
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{compId}")
    public void deleteCompilation(@PathVariable Integer compId) {
        service.deleteById(compId);
    }
//
//    @PatchMapping("/{compId}")
//    public Compilation patchCompilation(@PathVariable Integer compId) {
//        return null;
//    }
}
