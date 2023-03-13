package ru.practicum.mainserver.categories.service.admin;

import ru.practicum.mainserver.categories.model.Category;

public interface AdminCategoriesService {
    Category createCategory(Category category);

    void deleteCategoryById(Integer catId);

    Category patchCategory(Category toCategory);
}
