package ru.practicum.mainserver.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categories")
public class PublicCategoriesController {
    @GetMapping("")
    public String getCategories() {
        return null;
    }

    @GetMapping("/{catId}")
    public String geCategoryById(@PathVariable Integer catId) {
        return null;
    }
}
