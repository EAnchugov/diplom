package ru.practicum.mainserver.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.practicum.mainserver.Model.Category.Category;
import ru.practicum.mainserver.Model.Category.CategoryDto;
import ru.practicum.mainserver.Model.Category.CategoryMapper;
import ru.practicum.mainserver.service.category.AdminCategoriesService;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

@RestController
@RequestMapping("/admin/categories")
@RequiredArgsConstructor
public class AdminCategoriesController {
    private final AdminCategoriesService adminCategories;

    @PostMapping
    public CategoryDto addCategory(@Valid @RequestBody CategoryDto categoryDto) {
        Category category = adminCategories.createCategory(CategoryMapper.toCategory(categoryDto));
        return CategoryMapper.toCategoryDto(category);
    }

    @DeleteMapping("/catId")
    public void deleteCategory(@Positive @PathVariable Integer catId) {
        adminCategories.deleteCategoryById(catId);
    }

    @PatchMapping("/catId")
    public CategoryDto patchCategory(@Positive @PathVariable CategoryDto categoryDto) {
        Category category = adminCategories.patchCategory(CategoryMapper.toCategory(categoryDto));
        return null;
    }
}
