package ru.practicum.mainserver.categories.service.user;

import ru.practicum.mainserver.categories.model.Category;

import java.util.List;

public interface UserCategoryService {
    List<Category> getAll(Integer from, Integer size);

    Category getByID(Integer catId);
}
