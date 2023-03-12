package ru.practicum.mainserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.mainserver.model.Category.Category;

import java.awt.print.Pageable;
import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
