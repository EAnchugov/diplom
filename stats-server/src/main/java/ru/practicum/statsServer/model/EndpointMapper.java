package ru.practicum.statsServer.model;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import ru.practicum.statsServer.model.dto.EndpointDto;
import ru.practicum.statsServer.model.dto.EndpointDtoOutput;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

import static ru.practicum.statsServer.model.GlobalVariables.FORMAT;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EndpointMapper {

    public static Endpoint toEndpoint(EndpointDto dto) {
        Endpoint endpoint = new Endpoint();
        endpoint.setIp(dto.getIp());
        endpoint.setTimestamp(LocalDateTime.parse(URLDecoder.decode(dto.getTimestamp(),
                StandardCharsets.UTF_8), FORMAT));
        endpoint.setUri(dto.getUri());
        endpoint.setApp(dto.getApp());
        return endpoint;
    }

    public static EndpointDto toEndpointDto(Endpoint endpoint) {
        return EndpointDto.builder()
                .app(endpoint.getApp())
                .uri(endpoint.getUri())
                .ip(endpoint.getIp())
                .timestamp(endpoint.getTimestamp().format(FORMAT))
                .build();
    }

    public static EndpointDtoOutput toEndpointDtoOutput(Endpoint endpoint) {
        return EndpointDtoOutput.builder()
                .app(endpoint.getApp())
                .uri(endpoint.getUri())
                .hits(1)
                .build();
    }
}
