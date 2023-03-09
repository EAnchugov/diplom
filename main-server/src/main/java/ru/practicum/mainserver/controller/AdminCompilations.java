package ru.practicum.mainserver.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/compilations")
public class AdminCompilations {
    @PostMapping
    public String addCompilation(){
        return null;
    }
    @DeleteMapping("{compId}")
    public String deleteCompilation(@PathVariable Integer compId){
        return null;
    }

    @PatchMapping("{/compId}")
    public String patchCompilation(@PathVariable Integer compId ){
        return null;
    }
}
