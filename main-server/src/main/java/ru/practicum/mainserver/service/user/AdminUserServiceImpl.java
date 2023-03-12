package ru.practicum.mainserver.service.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.mainserver.Model.User.User;
import ru.practicum.mainserver.repository.UserRepository;

@RequiredArgsConstructor
@Service
public class AdminUserServiceImpl implements AdminUserService {
    private final UserRepository repository;

    @Override
    public User create(User user) {
        return repository.save(user);
    }

    @Override
    public void deleteUser(Integer userId) {
        repository.deleteById(userId);
    }
}
