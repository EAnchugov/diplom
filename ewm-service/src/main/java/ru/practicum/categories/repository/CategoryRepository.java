package ru.practicum.categories.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.categories.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
