package ru.practicum.categories.service.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.categories.model.Category;
import ru.practicum.categories.model.CategoryDto;
import ru.practicum.categories.repository.CategoryRepository;
import ru.practicum.categories.service.user.UserCategoryService;
import ru.practicum.events.service.EventsService;
import ru.practicum.exceptions.WrongParameterException;

@Service
@RequiredArgsConstructor
public class AdminCategoryServiceImpl implements AdminCategoriesService {
    private final CategoryRepository repository;
    private final UserCategoryService userCategoryService;
    private final EventsService eventsService;

    @Override
    @Transactional
    public Category createCategory(Category category) {
        try {
            return repository.saveAndFlush(category);
        } catch (DataIntegrityViolationException exception) {
            throw new WrongParameterException("Имя должно быть уникальным");
        }
    }

    @Override
    @Transactional
    public void deleteCategoryById(Integer catId) {
        if (eventsService.getByCategoryId(catId).size() == 0) {
            repository.delete(repository.getById(catId));
        } else {
            throw new WrongParameterException("С категорией связаны события");
        }
    }

    @Override
    @Transactional
    public Category patchCategory(Integer catId, CategoryDto categoryDto) {
        Category patch = userCategoryService.getByID(catId);
            try {
                patch.setName(categoryDto.getName());
              return repository.saveAndFlush(patch);
        } catch (DataIntegrityViolationException exception) {
            throw new WrongParameterException("Имя должно быть уникальным");
        }
    }
}
