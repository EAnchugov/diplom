package ru.practicum.compilations.model;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import ru.practicum.events.model.Event;

import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CompilationMapper {
    public static Compilation toCompilation(CompilationDto compilationDto) {
     return Compilation.builder()
             .title(compilationDto.getTitle())
             .pinned(compilationDto.getPinned())
             .build();
    }


    public static CompilationDto toCompilationDto(Compilation compilation) {
        return CompilationDto.builder()
                .id(compilation.getId())
                .pinned(compilation.getPinned())
                .title(compilation.getTitle())
                .events(compilation.getEvents().stream().map(Event::getId).collect(Collectors.toList()))
                .build();
    }

    public static Compilation toCompilation(CompilationDtoInput compilationDtoInput) {
        return Compilation.builder()
                .title(compilationDtoInput.getTitle())
                .pinned(compilationDtoInput.getPinned())
                .build();
    }

    public static CompilationDtoOutput toOutput(Compilation compilation) {
        return CompilationDtoOutput.builder()
                .id(compilation.getId())
                .title(compilation.getTitle())
                .pinned(compilation.getPinned())
                .build();
    }
}
