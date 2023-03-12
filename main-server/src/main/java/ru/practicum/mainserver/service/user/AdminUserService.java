package ru.practicum.mainserver.service.user;

import ru.practicum.mainserver.model.User.User;
import ru.practicum.mainserver.model.User.UserDto;

import java.util.List;

public interface AdminUserService {
    User create(User toUser);

    void deleteUser(Integer userId);

    List<User> getUsers(List<Integer> users, Integer from, Integer size);
}
