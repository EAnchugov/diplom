package ru.practicum.mainserver.categories.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.mainserver.categories.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
