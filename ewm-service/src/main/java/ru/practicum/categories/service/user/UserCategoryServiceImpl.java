package ru.practicum.categories.service.user;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.practicum.categories.model.Category;
import ru.practicum.categories.repository.CategoryRepository;
import ru.practicum.exceptions.ItemNotAvailableException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserCategoryServiceImpl implements UserCategoryService {
    private final CategoryRepository repository;

    @Override
    public List<Category> getAll(Integer from, Integer size) {
        Pageable pageable = PageRequest.of(from,size);
        return repository.findAll(pageable).stream().collect(Collectors.toList());
    }

    @Override
    public Category getByID(Integer catId) {
        return repository.findById(catId).orElseThrow(() -> new ItemNotAvailableException("Нет такой категории"));
    }

    @Override
    public List<Category> getAllCategories() {
        return repository.findAll();
    }

}
