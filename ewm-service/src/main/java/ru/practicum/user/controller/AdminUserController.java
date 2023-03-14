package ru.practicum.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.practicum.user.service.AdminUserService;
import ru.practicum.user.dto.User;
import ru.practicum.user.dto.UserDto;
import ru.practicum.user.dto.UserMapper;

import javax.validation.Valid;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/admin/users")
public class AdminUserController {
    private final AdminUserService service;

    @GetMapping
    public List<UserDto> getUser(@RequestParam(required = false) List<Integer> users,
                          @PositiveOrZero @RequestParam(name = "from", defaultValue = "0") Integer from,
                          @PositiveOrZero @RequestParam(name = "size", defaultValue = "10") Integer size) {
        return service.getUsers(users,from,size).stream().map(UserMapper::toUserDto).collect(Collectors.toList());
    }

    @PostMapping
    public UserDto addUser(@Valid @RequestBody UserDto userDto) {
        User user = service.create(UserMapper.toUser(userDto));
        return UserMapper.toUserDto(user);
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable Integer userId) {
        service.deleteUser(userId);
    }
}
