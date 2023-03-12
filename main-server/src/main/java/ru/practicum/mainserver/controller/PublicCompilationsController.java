package ru.practicum.mainserver.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/compilations")
public class PublicCompilationsController {
    @GetMapping
    public String getAllCompilations(@RequestParam Boolean pinned,
                                  @RequestParam Integer from,
                                  @RequestParam Integer size) {
        return null;
    }

    @GetMapping("/{complId}")
    public String getCompilations(@PathVariable Integer complId) {
        return null;
    }
}
