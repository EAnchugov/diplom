package ru.practicum.request.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.request.model.EventRequestStatusUpdateResult;
import ru.practicum.request.model.RequestDtoOutput;
import ru.practicum.request.model.RequestMapper;
import ru.practicum.request.model.RequestsUpdateDto;
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
    public List<RequestDtoOutput> getUserRequests(@PathVariable Integer userId) {
        return service.getByUserId(userId).stream()
                .map(RequestMapper::toOutput).collect(Collectors.toList());
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{userId}/requests")
    public RequestDtoOutput addUserRequest(@PathVariable(required = false) @Positive Integer userId,
                                           @RequestParam(required = false) @Positive  Integer eventId) {
        return RequestMapper.toOutput(service.create(userId,eventId));
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/{userId}/events/{eventId}/requests")
    public EventRequestStatusUpdateResult updateRequest(@Positive @PathVariable Integer userId,
                                                        @Positive @PathVariable Integer eventId,
                                                        @RequestBody(required = false) @Validated
                                                            RequestsUpdateDto requestsUpdateDto) {
        return service.update(userId,eventId,requestsUpdateDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{userId}/events/{eventId}/requests")
    public List<RequestDtoOutput> getEventsWithUserRequest(@Positive @PathVariable Integer userId,
                                                 @Positive @PathVariable Integer eventId) {
        return service.getEventsWithUserRequest(userId,eventId).stream()
                .map(RequestMapper::toOutput).collect(Collectors.toList());
    }

    @PatchMapping("/{userId}/requests/{requestId}/cancel")
    public RequestDtoOutput deleteUserRequest(@PathVariable Integer userId,
                                    @PathVariable Integer requestId) {
        return RequestMapper.toOutput(service.cancel(userId,requestId));
    }
}
