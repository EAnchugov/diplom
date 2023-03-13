package ru.practicum.mainserver.categories.service.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.mainserver.categories.model.Category;
import ru.practicum.mainserver.categories.repository.CategoryRepository;

@Service
@RequiredArgsConstructor
public class AdminCategoryServiceImpl implements AdminCategoriesService {
    private final CategoryRepository repository;

    @Override
    public Category createCategory(Category category) {
        return repository.save(category);
    }

    @Override
    public void deleteCategoryById(Integer catId) {
        //Обратите внимание: с категорией не должно быть связано ни одного события.
        repository.deleteById(catId);
    }

    @Override
    public Category patchCategory(Category category) {
        Category patch = repository.getReferenceById(category.getId());
        patch.setName(category.getName());
        return repository.save(patch);
    }
}
