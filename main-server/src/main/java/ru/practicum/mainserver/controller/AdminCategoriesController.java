package ru.practicum.mainserver.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.practicum.mainserver.model.Category.Category;
import ru.practicum.mainserver.model.Category.CategoryDto;
import ru.practicum.mainserver.model.Category.CategoryMapper;
import ru.practicum.mainserver.service.category.admin.AdminCategoriesService;

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
