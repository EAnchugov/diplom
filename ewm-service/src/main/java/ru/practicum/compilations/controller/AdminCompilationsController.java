package ru.practicum.compilations.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.compilations.model.Compilation;
import ru.practicum.compilations.model.CompilationDtoInput;
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
        return new Compilation();
    }

//    @DeleteMapping("/{compId}")
//    public void deleteCompilation(@PathVariable Integer compId) {
//    }
//
//    @PatchMapping("/{compId}")
//    public Compilation patchCompilation(@PathVariable Integer compId) {
//        return null;
//    }
}
