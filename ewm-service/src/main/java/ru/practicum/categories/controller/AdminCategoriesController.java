package ru.practicum.categories.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.categories.model.Category;
import ru.practicum.categories.model.CategoryDto;
import ru.practicum.categories.model.CategoryMapper;
import ru.practicum.categories.service.admin.AdminCategoriesService;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

@RestController
@RequestMapping("/admin/categories")
@RequiredArgsConstructor
@Slf4j
public class AdminCategoriesController {
    private final AdminCategoriesService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryDto addCategory(@Valid @RequestBody CategoryDto categoryDto) {
        return CategoryMapper.toCategoryDto(service.createCategory(
                Category.builder().name(categoryDto.getName()).build()));
    }

    @DeleteMapping("/{catId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCategory(@Positive @PathVariable Integer catId) {
        service.deleteCategoryById(catId);
    }

    @PatchMapping("/{catId}")
    public CategoryDto patchCategory(@PathVariable Integer catId,
                                     @Valid CategoryDto categoryDto) {
        log.info("Получен запрос на изменение {catId} {categoryDto}" + catId);
        Category category = service.patchCategory(catId,categoryDto);
        return CategoryMapper.toCategoryDto(category);
    }
}
