package ru.practicum.mainserver.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/users")
public class AdminUserController {
    @GetMapping
    public String getUser(){
        return null;
    }
    @PostMapping
    public String addUser(){
        return null;
    }
    @DeleteMapping("/{userId}")
    public String deleteUser(@PathVariable Integer userId){
        return null;
    }
}
