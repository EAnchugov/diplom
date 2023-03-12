package ru.practicum.mainserver.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.practicum.mainserver.Model.Compilation.Compilation;
import ru.practicum.mainserver.Model.Compilation.CompilationDto;
import ru.practicum.mainserver.Model.Compilation.CompilationMapper;
import ru.practicum.mainserver.service.compilation.AdminCompilationService;

import javax.validation.Valid;
@RequiredArgsConstructor
@RestController
@RequestMapping("/admin/compilations")
public class AdminCompilationsController {
//    private final AdminCompilationService service;
//    @PostMapping
//    public CompilationDto addCompilation(@Valid @RequestBody CompilationDto compilationDto){
//        Compilation compilation = service.create(CompilationMapper.toCompilation(compilationDto));
//        return CompilationMapper.toCompilationDto(compilation);
//    }
//    @DeleteMapping("{compId}")
//    public String deleteCompilation(@PathVariable Integer compId){
//        return null;
//    }
//
//    @PatchMapping("{/compId}")
//    public String patchCompilation(@PathVariable Integer compId ){
//        return null;
//    }
}
