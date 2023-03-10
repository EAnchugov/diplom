package ru.practicum.mainserver.service.User;

import ru.practicum.mainserver.Model.User.User;

public interface AdminUserService {
    User create(User toUser);

    void deleteUser(Integer userId);
}
