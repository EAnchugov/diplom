package ru.practicum.request.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class EventRequestStatusUpdateResult {
    List<RequestDtoOutput> confirmedRequests = new ArrayList<>();
    List<RequestDtoOutput> rejectedRequests = new ArrayList<>();
}
