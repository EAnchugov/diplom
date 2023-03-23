package ru.practicum.user.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.practicum.user.model.User;
import ru.practicum.user.model.UserDto;
import ru.practicum.user.model.UserMapper;
import ru.practicum.user.service.AdminUserService;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/admin/users")
public class AdminUserController {
    private final AdminUserService service;

    @GetMapping
    public List<UserDto> getUser(@RequestParam(required = false) List<Integer> users,
                          @RequestParam(defaultValue = "0") @Min(0) Integer from,
                          @RequestParam(defaultValue = "10") @Min(1) Integer size) {
        return ResponseEntity.status(HttpStatus.OK).body(
                service.getUsers(users,from,size).stream().map(UserMapper::toUserDto).collect(Collectors.toList()))
                .getBody();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto addUser(@Valid @RequestBody UserDto userDto) {
        User user = service.create(UserMapper.toUser(userDto));
        return UserMapper.toUserDto(user);
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Integer userId) {
        service.deleteUser(userId);
    }
}
