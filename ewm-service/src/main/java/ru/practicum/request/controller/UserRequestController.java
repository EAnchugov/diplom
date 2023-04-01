package ru.practicum.request.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.request.model.RequestDtoOutput;
import ru.practicum.request.model.RequestMapper;
import ru.practicum.request.model.RequestsUpdateDto;
import ru.practicum.request.service.RequestService;

import javax.validation.constraints.Positive;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserRequestController {
    private final RequestService service;

    @GetMapping("/{userId}/requests")
    public String getUserRequests(@PathVariable Integer userId) {
        return null;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{userId}/requests")
    public RequestDtoOutput addUserRequest(@Positive @PathVariable Integer userId,
                                           @RequestParam @Positive Integer eventId) {
        return RequestMapper.toOutput(service.create(userId,eventId));
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/{userId}/events/{eventId}/requests")
    public List<RequestDtoOutput> updateRequests(@Positive @PathVariable Integer userId,
                                                 @Positive @PathVariable Integer eventId,
                                                 @RequestBody RequestsUpdateDto updateDto) {
        service.update(userId,eventId,updateDto);
        return new ArrayList<>();
    }


//    @ResponseStatus(HttpStatus.OK)
//    @PatchMapping("/{userId}/events/{eventId}/requests")
//    public List<RequestDtoOutput> patchRequest(@PathVariable Integer userId,
//                                                        @PathVariable Integer eventId,
//                                                        @RequestBody @Validated
//                                                        EventRequestStatusUpdateRequest updateRequest) {
////        List<Request> requests = service.update(userId,eventId,updateRequest);
//////        return service.updateEventStatus(userId, eventId, upda
//////        teRequest).stream()
//////                        .map(EventsMapper::eventToOutput).collect(Collectors.toList());
////        return requests.stream().map(RequestMapper::toOutput).collect(Collectors.toList());
//        List<RequestDtoOutput> out = new ArrayList<>();
//        out.add(new RequestDtoOutput());
//        return out;
//    }

    @DeleteMapping("/{userId}/requests/{requestId}/cancel")
    public String deleteUserRequest(@PathVariable Integer userId,
                                    @PathVariable Integer requestId) {
        return null;
    }
}
