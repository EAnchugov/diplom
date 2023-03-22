package ru.practicum.compilations.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
                                                   @RequestParam(defaultValue = "0") Integer from,
                                                   @RequestParam(defaultValue = "10") Integer size) {
        return ResponseEntity.status(HttpStatus.OK).body(
                compilationService.getAll(pinned,from,size).stream().map(CompilationMapper::toCompilationDto)
                        .collect(Collectors.toList()))
                .getBody();
    }

    @GetMapping("/{compId}")
    public String getCompilations(@PathVariable Integer compId) {
        return null;
    }
}
