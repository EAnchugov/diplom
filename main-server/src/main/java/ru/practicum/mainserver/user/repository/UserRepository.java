package ru.practicum.mainserver.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.mainserver.user.dto.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Integer> {
    List<User> findAllByIdIn(List<Integer> ids);
}
