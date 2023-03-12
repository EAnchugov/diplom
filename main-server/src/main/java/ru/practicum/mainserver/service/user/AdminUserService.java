package ru.practicum.mainserver.service.user;

import ru.practicum.mainserver.model.User.User;

public interface AdminUserService {
    User create(User toUser);

    void deleteUser(Integer userId);
}
