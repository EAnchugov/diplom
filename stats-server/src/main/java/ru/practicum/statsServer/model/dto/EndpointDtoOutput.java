package ru.practicum.statsServer.model.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class EndpointDtoOutput {
        private String app;
        private String uri;
        private long hits;
}
