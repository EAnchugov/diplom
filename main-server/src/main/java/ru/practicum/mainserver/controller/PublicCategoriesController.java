package ru.practicum.mainserver.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.web.bind.annotation.*;
import ru.practicum.mainserver.model.Category.Category;
import ru.practicum.mainserver.model.Category.CategoryDto;
import ru.practicum.mainserver.model.Category.CategoryMapper;
import ru.practicum.mainserver.service.category.user.UserCategoryService;

import javax.validation.constraints.PositiveOrZero;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class PublicCategoriesController {
    public final UserCategoryService service;
    @GetMapping("")
    public List<CategoryDto> getCategories(
            @PositiveOrZero @RequestParam(name = "from", defaultValue = "0") Integer from,
            @PositiveOrZero @RequestParam(name = "size", defaultValue = "10") Integer size) {
        List<Category> categories = new ArrayList<>();
        categories = service.getAll(from, size);
        return categories.stream().map(CategoryMapper :: toCategoryDto)
                .sorted(Comparator.comparing(CategoryDto::getId)).collect(Collectors.toList());
    }

    @GetMapping("/{catId}")
    public CategoryDto geCategoryById(@PathVariable Integer catId) {
        return CategoryMapper.toCategoryDto(service.getByID(catId));
    }
}
