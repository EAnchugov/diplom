package ru.practicum.request.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.practicum.request.model.RequestDtoOutput;
import ru.practicum.request.model.RequestMapper;

import javax.validation.constraints.Positive;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserRequestController {
    private final RequestController controller;

    @GetMapping("/{userId}/requests")
    public String getUserRequests(@PathVariable Integer userId) {
        return null;
    }

    @PostMapping("/{userId}/requests")
    public RequestDtoOutput addUserRequest(@Positive @PathVariable Integer userId,
                                           @RequestParam @Positive Integer eventId) {
        return RequestMapper.toOutput(controller.create(userId,eventId));
    }

    @DeleteMapping("/{userId}/requests/{requestId}/cancel")
    public String deleteUserRequest(@PathVariable Integer userId,
                                    @PathVariable Integer requestId) {
        return null;
    }
}
