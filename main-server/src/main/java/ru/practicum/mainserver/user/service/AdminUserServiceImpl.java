package ru.practicum.mainserver.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.practicum.mainserver.user.repository.UserRepository;
import ru.practicum.mainserver.user.dto.User;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.data.domain.Sort.Direction.DESC;

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
        Pageable pageable = PageRequest.of(from > 0 ? from / size : 0, size, Sort.by(DESC, "start"));
        if (users.isEmpty()) {
            return repository.findAll(pageable).stream().collect(Collectors.toList());
        } else {
            return repository.findAllByIdIn(users);
        }
    }

    @Override
    public User getById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Нет Юзера с ID = " + id));
    }
}
