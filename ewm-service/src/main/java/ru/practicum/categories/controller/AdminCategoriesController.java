package ru.practicum.categories.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.practicum.categories.model.CategoryMapper;
import ru.practicum.categories.service.admin.AdminCategoriesService;
import ru.practicum.categories.model.Category;
import ru.practicum.categories.model.CategoryDto;

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
    public CategoryDto patchCategory(@PathVariable Integer catId,
                                     @RequestBody CategoryDto categoryDto) {
        Category category = service.patchCategory(catId,categoryDto);
        return CategoryMapper.toCategoryDto(category);
    }
}
