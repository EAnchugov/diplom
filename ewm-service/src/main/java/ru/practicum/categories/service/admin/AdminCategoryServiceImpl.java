package ru.practicum.categories.service.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import ru.practicum.categories.repository.CategoryRepository;
import ru.practicum.categories.model.Category;

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
    public Category patchCategory(Integer catId) {
        Category patch = repository.findById(catId)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR,"Нет категории с нужным ID"));
//        patch.setName(categoryDto.getName());
        return repository.save(patch);
    }
}
