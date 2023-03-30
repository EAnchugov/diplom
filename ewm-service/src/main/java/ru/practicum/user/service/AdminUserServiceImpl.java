package ru.practicum.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.practicum.exceptions.WrongParameterException;
import ru.practicum.user.model.User;
import ru.practicum.user.repository.UserRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AdminUserServiceImpl implements AdminUserService {
    private final UserRepository repository;

    @Override
    public User create(User user) {
            try {
                return repository.saveAndFlush(user);
            } catch (DataIntegrityViolationException exception) {
                throw new WrongParameterException("Имя должно быть уникальным");
            }
    }

    @Override
    public void deleteUser(Integer userId) {
        repository.delete(getById(userId));
    }

    @Override
    public List<User> getUsers(List<Integer> users, Integer from, Integer size) {
        Pageable pageable = PageRequest.of(from, size);
        if (users == null) {
            return repository.findAll(pageable).toList();
        } else {
//            if (users.isEmpty()) {
//                return repository.findAll(pageable).stream().collect(Collectors.toList());
//            } else {
                return repository.findAllById(users);
            }
//        }
    }

    @Override
    public User getById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Нет Юзера с ID = " + id));
    }
}
