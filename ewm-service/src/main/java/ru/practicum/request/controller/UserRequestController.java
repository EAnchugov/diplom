package ru.practicum.request.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.events.model.EventRequestStatusUpdateRequest;
import ru.practicum.request.model.Request;
import ru.practicum.request.model.RequestDtoOutput;
import ru.practicum.request.model.RequestMapper;
import ru.practicum.request.service.RequestService;

import javax.validation.constraints.Positive;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserRequestController {
    private final RequestService service;

    @GetMapping("/{userId}/requests")
    public String getUserRequests(@PathVariable Integer userId) {
        return null;
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/{userId}/requests")
    public RequestDtoOutput addUserRequest(@Positive @PathVariable Integer userId,
                                           @RequestParam @Positive Integer eventId) {
        return RequestMapper.toOutput(service.create(userId,eventId));
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/{userId}/events/{eventId}/requests")
    public List<RequestDtoOutput> patchUserEventRequest(@PathVariable Integer userId,
                                                        @PathVariable Integer eventId,
                                                        @RequestBody EventRequestStatusUpdateRequest updateRequest) {
        List<Request> requests = service.update(userId,eventId,updateRequest);
//        return service.updateEventStatus(userId, eventId, upda
//        teRequest).stream()
//                        .map(EventsMapper::eventToOutput).collect(Collectors.toList());
        return requests.stream().map(RequestMapper::toOutput).collect(Collectors.toList());
    }

    @DeleteMapping("/{userId}/requests/{requestId}/cancel")
    public String deleteUserRequest(@PathVariable Integer userId,
                                    @PathVariable Integer requestId) {
        return null;
    }
}
