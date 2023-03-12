package ru.practicum.mainserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.mainserver.model.User.User;

public interface UserRepository extends JpaRepository<User,Integer> {
}
