package ru.practicum.categories.service.admin;

import ru.practicum.categories.model.Category;
import ru.practicum.categories.model.CategoryDto;

public interface AdminCategoriesService {
    Category createCategory(Category category);

    void deleteCategoryById(Integer catId);

    Category patchCategory(Integer catId, CategoryDto categoryDto);
}
