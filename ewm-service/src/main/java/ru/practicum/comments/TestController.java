package ru.practicum.comments;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.compilations.model.CompilationDtoInput;

@RequiredArgsConstructor
@RestController
@RequestMapping("/comments")
public class TestController {
    @GetMapping
    public String createCompilation(@RequestBody @Validated CompilationDtoInput input) {
        return "testOK";
    }
}
