package ru.practicum.mainserver.service.category.user;

import lombok.RequiredArgsConstructor;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.practicum.mainserver.model.Category.Category;
import ru.practicum.mainserver.repository.CategoryRepository;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.data.domain.Sort.Direction.DESC;

@Service
@RequiredArgsConstructor
public class UserCategoryServiceImpl implements UserCategoryService {
    private final CategoryRepository repository;

    @Override
    public List<Category> getAll(Integer from, Integer size) {
        Pageable pageable = PageRequest.of(from > 0 ? from / size : 0, size, Sort.by(DESC, "start"));
        return repository.findAll(pageable).stream().collect(Collectors.toList());
    }

    @Override
    public Category getByID(Integer catId) {
        return repository.findById(catId).orElseThrow(() -> new IllegalArgumentException("Нет такой категории"));
    }

}
