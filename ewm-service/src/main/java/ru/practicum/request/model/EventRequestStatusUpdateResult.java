package ru.practicum.request.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class EventRequestStatusUpdateResult {
    List<RequestDtoOutput> confirmedRequests;
    List<RequestDtoOutput> rejectedRequests;
}
