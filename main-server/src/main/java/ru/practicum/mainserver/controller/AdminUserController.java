package ru.practicum.mainserver.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.practicum.mainserver.Model.User.User;
import ru.practicum.mainserver.Model.User.UserDto;
import ru.practicum.mainserver.Model.User.UserMapper;
import ru.practicum.mainserver.service.User.AdminUserService;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/admin/users")
public class AdminUserController {
    private final AdminUserService service;
    @GetMapping
    public String getUser(){
        return null;
    }
    @PostMapping
    public UserDto addUser(@Valid @RequestBody UserDto userDto){
        User user = service.create(UserMapper.toUser(userDto));
        return UserMapper.toUserDto(user);
    }
    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable Integer userId){
        service.deleteUser(userId);
    }
}
