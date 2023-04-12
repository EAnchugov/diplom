package ru.practicum.request.model;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class EventRequestStatusUpdateResult {
    List<RequestDtoOutput> confirmedRequests = new ArrayList<>();
    List<RequestDtoOutput> rejectedRequests = new ArrayList<>();
}
