package ru.practicum.mainserver.categories.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.practicum.mainserver.categories.model.Category;
import ru.practicum.mainserver.categories.model.CategoryDto;
import ru.practicum.mainserver.categories.model.CategoryMapper;
import ru.practicum.mainserver.categories.service.admin.AdminCategoriesService;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

@RestController
@RequestMapping("/admin/categories")
@RequiredArgsConstructor
public class AdminCategoriesController {
    private final AdminCategoriesService service;

    @PostMapping
    public CategoryDto addCategory(@Valid @RequestBody CategoryDto categoryDto) {
        Category category = service.createCategory(CategoryMapper.toCategory(categoryDto));
        return CategoryMapper.toCategoryDto(category);
    }

    @DeleteMapping("/catId")
    public void deleteCategory(@Positive @PathVariable Integer catId) {
        service.deleteCategoryById(catId);
    }

    @PatchMapping("/catId")
    public CategoryDto patchCategory(@Positive @PathVariable CategoryDto categoryDto) {
        Category category = service.patchCategory(CategoryMapper.toCategory(categoryDto));
        return null;
    }
}
