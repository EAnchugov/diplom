package ru.practicum.events.model;

import lombok.*;
import ru.practicum.http.EndpointDtoOutput;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EventDtoOutput2 {
            private List<EndpointDtoOutput> events;

}
