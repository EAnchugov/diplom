package ru.practicum.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.practicum.user.model.User;
import ru.practicum.user.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public List<User> getUsers(List<Integer> users, Integer from, Integer size) {
        Pageable pageable = PageRequest.of(from, size);
        if (users == null) {
            return repository.findAll(pageable).toList();
        } else {
            if (users.isEmpty()) {
                return repository.findAll(pageable).stream().collect(Collectors.toList());
            } else {
                return repository.findAllByIdIn(users,pageable);
            }
        }
    }

    @Override
    public User getById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Нет Юзера с ID = " + id));
    }
}
