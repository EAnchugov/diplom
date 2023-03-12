package ru.practicum.mainserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.mainserver.model.User.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Integer> {
    List<User> findAllByIdIn(List<Integer> ids);
}
