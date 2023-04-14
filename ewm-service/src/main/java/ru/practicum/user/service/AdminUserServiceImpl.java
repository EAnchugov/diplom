package ru.practicum.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.exceptions.WrongParameterException;
import ru.practicum.user.model.User;
import ru.practicum.user.repository.UserRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AdminUserServiceImpl implements AdminUserService {
    private final UserRepository repository;

    @Override
    @Transactional
    public User create(User user) {
            try {
                return repository.saveAndFlush(user);
            } catch (DataIntegrityViolationException exception) {
                throw new WrongParameterException("Имя должно быть уникальным");
            }
    }

    @Override
    @Transactional
    public void deleteUser(Integer userId) {
        repository.delete(getById(userId));
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public List<User> getUsers(List<Integer> users, Integer from, Integer size) {
        Pageable pageable = PageRequest.of(from, size);
        if (users == null) {
            return repository.findAll(pageable).toList();
        } else {
                return repository.findAllById(users);
        }
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public User getById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Нет Юзера с ID = " + id));
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public List<User> getAllUsers() {
        return repository.findAll();
    }
}
