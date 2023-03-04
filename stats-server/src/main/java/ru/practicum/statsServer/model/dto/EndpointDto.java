package ru.practicum.statsServer.model.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@Data
public class EndpointDto {
    private String app;
    private String uri;
    private String ip;
    private String timestamp;
}
