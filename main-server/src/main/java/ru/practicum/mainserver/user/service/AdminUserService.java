package ru.practicum.mainserver.user.service;

import ru.practicum.mainserver.user.dto.User;

import java.util.List;

public interface AdminUserService {
    User create(User toUser);

    void deleteUser(Integer userId);

    List<User> getUsers(List<Integer> users, Integer from, Integer size);

    User getById(Integer id);
}
