package ru.practicum.mainserver.service.category.user;

import ru.practicum.mainserver.model.Category.Category;

import java.util.List;

public interface UserCategoryService {
    List<Category> getAll(Integer from, Integer size);

    Category getByID(Integer catId);
}
