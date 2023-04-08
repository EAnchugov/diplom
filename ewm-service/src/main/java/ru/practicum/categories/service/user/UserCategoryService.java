package ru.practicum.categories.service.user;

import ru.practicum.categories.model.Category;

import java.util.List;

public interface UserCategoryService {
    List<Category> getAll(Integer from, Integer size);

    Category getByID(Integer catId);

    List<Category> getAllCategories();
}
