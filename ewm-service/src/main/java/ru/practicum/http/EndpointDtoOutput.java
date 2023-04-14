package ru.practicum.http;

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
