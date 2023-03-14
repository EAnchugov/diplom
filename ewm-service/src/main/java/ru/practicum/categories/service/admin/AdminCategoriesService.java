package ru.practicum.categories.service.admin;

import ru.practicum.categories.model.Category;

public interface AdminCategoriesService {
    Category createCategory(Category category);

    void deleteCategoryById(Integer catId);

    Category patchCategory(Category toCategory);
}
