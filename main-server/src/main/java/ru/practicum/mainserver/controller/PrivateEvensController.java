package ru.practicum.mainserver.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class PrivateEvensController {
    @GetMapping("/{userId}/events")
    public String getUserEvents(@PathVariable Integer userId){
        return null;
    }
    @PostMapping("/{userId}/events")
    public String addUserEvents(@PathVariable Integer userId){
        return null;
    }
    @GetMapping("/{userId}/events/{eventId}")
    public String getUserEvents(@PathVariable Integer userId,
                                @PathVariable Integer eventId){
        return null;
    }
    @PatchMapping("/{userId}/events/{eventId}")
    public String patchUserEvent(@PathVariable Integer userId,
                                 @PathVariable Integer eventId){
        return null;
    }

    @GetMapping("/{userId}/events/{eventId}/requests")
    public String getUserEventRequest(@PathVariable Integer userId,
                                      @PathVariable Integer eventId){
        return null;
    }

    @PatchMapping("/{userId}/events/{eventId}/requests")
    public String patchUserEventRequest(@PathVariable Integer userId,
                                      @PathVariable Integer eventId){
        return null;
    }
}
