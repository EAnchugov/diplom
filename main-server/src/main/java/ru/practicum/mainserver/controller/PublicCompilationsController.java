package ru.practicum.mainserver.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/compilations")
public class PublicCompilationsController {
    @GetMapping
    public String GetAllCompilations(@RequestParam Boolean pinned,
                                  @RequestParam Integer from,
                                  @RequestParam Integer size){
        return null;
    }

    @GetMapping("/{complId}")
    public String GetCompilations(@PathVariable Integer complId){
        return null;
    }
}
