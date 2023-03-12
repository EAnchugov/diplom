package ru.practicum.mainserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.mainserver.model.Category.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
