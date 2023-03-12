package ru.practicum.mainserver.Model.Compilation;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CompilationMapper {
    public static Compilation toCompilation(CompilationDto compilationDto) {
     return Compilation.builder()
             .title(compilationDto.getTitle())
             .id(compilationDto.getId())
             .title(compilationDto.getTitle())
             .pinned(compilationDto.getPinned())
             .build();
    }

    public static CompilationDto toCompilationDto(Compilation compilation) {
        return CompilationDto.builder()
                .id(compilation.getId())
                .pinned(compilation.getPinned())
                .title(compilation.getTitle())
                .build();
    }
}
