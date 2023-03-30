package ru.practicum.categories.service.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.categories.model.CategoryDto;
import ru.practicum.categories.repository.CategoryRepository;
import ru.practicum.categories.model.Category;
import ru.practicum.categories.service.user.UserCategoryService;
import ru.practicum.exceptions.WrongParameterException;

@Service
@RequiredArgsConstructor
public class AdminCategoryServiceImpl implements AdminCategoriesService {
    private final CategoryRepository repository;
    private final UserCategoryService userCategoryService;

    @Override
    public Category createCategory(Category category) {
        return repository.save(category);
    }

    @Override
    public void deleteCategoryById(Integer catId) {
        //Обратите внимание: с категорией не должно быть связано ни одного события.
        repository.delete(repository.getById(catId));
    }

    @Override
    @Transactional
    public Category patchCategory(Integer catId, CategoryDto categoryDto) {
        Category patch = userCategoryService.getByID(catId);
        if (patch.getName().toLowerCase().equals(categoryDto.getName().toLowerCase())) {
            throw new WrongParameterException("Имя должно быть уникальным");
        }
        return repository.save(patch);
    }
}
