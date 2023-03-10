package ru.practicum.mainserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.mainserver.Model.User.User;

public interface UserRepository extends JpaRepository<User,Integer>{

}
