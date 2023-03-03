package ru.practicum.statsServer.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class EndpointDto {
    private Long id;
    private String app;
    private String uri;
    private String ip;
    private LocalDateTime timestamp;

}
