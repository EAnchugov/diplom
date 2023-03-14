package ru.practicum.mainserver.compilations.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.practicum.mainserver.compilations.model.Compilation;
import ru.practicum.mainserver.compilations.model.CompilationDto;
import ru.practicum.mainserver.compilations.model.CompilationMapper;
import ru.practicum.mainserver.compilations.service.AdminCompilationService;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/admin/compilations")
public class AdminCompilationsController {
    private final AdminCompilationService service;
    @PostMapping
    public CompilationDto addCompilation(@Valid @RequestBody CompilationDto compilationDto){
        Compilation compilation = service.create(CompilationMapper.toCompilation(compilationDto));
        return CompilationMapper.toCompilationDto(compilation);
    }
    @DeleteMapping("/{compId}")
    public String deleteCompilation(@PathVariable Integer compId){
        return null;
    }

    @PatchMapping("/{compId}")
    public String patchCompilation(@PathVariable Integer compId ){
        return null;
    }
}
