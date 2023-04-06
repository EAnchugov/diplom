package ru.practicum.compilations.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.practicum.compilations.model.CompilationDto;
import ru.practicum.compilations.service.CompilationService;

import java.util.List;

@RestController
@RequestMapping("/compilations")
@AllArgsConstructor
@Slf4j
public class PublicCompilationsController {
    private final CompilationService compilationService;

    @GetMapping
    public List<CompilationDto> getAllCompilations(@RequestParam(defaultValue = "false") Boolean pinned,
                                                   @RequestParam(defaultValue = "0") Integer from,
                                                   @RequestParam(defaultValue = "10") Integer size) {
//        log.info("Получен запрос getAllCompilations pinned={}, from={}, size={}", pinned, from, size);
//        return compilationService.getAll(pinned,from,size).stream().map(CompilationMapper::toCompilationDto)
//                        .collect(Collectors.toList());
        return null;
    }

    @GetMapping("/{compId}")
    public String getCompilations(@PathVariable Integer compId) {
        return null;
    }
}
