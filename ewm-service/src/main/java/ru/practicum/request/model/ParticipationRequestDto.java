package ru.practicum.request.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class ParticipationRequestDto {
    private String description;
    private LocalDateTime created;
    private Integer eventId;
    private Integer id;
    private Integer requesterId;
    private String status;

}
