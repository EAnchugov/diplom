package ru.practicum.mainserver.service.category;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.mainserver.Model.Category.Category;

@Service
@RequiredArgsConstructor
public class AdminCategoryServiceImpl implements AdminCategoriesService{
    @Override
    public Category createCategory(Category category) {
        return null;
    }
}
