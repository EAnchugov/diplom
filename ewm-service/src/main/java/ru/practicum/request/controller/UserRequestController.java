package ru.practicum.request.controller;

import org.springframework.web.bind.annotation.*;
import ru.practicum.request.model.ParticipationRequestDto;

@RestController
@RequestMapping("/users")
public class UserRequestController {
    @GetMapping("/{userId}/requests")
    public String getUserRequests(@PathVariable Integer userId) {
        return null;
    }

    @PostMapping("/{userId}/requests")
    public String addUserRequest(@PathVariable Integer userId,
                                 @RequestBody ParticipationRequestDto requestDto) {
        return null;
    }

    @DeleteMapping("/{userId}/requests/{requestId}/cancel")
    public String deleteUserRequest(@PathVariable Integer userId,
                                    @PathVariable Integer requestId) {
        return null;
    }
}
