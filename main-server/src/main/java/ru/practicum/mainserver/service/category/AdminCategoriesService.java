package ru.practicum.mainserver.service.category;

import ru.practicum.mainserver.model.Category.Category;

public interface AdminCategoriesService {
    Category createCategory(Category category);

    void deleteCategoryById(Integer catId);

    Category patchCategory(Category toCategory);
}
