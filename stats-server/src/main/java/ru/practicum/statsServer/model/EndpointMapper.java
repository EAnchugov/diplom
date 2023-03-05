package ru.practicum.statsServer.model;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import ru.practicum.statsServer.model.dto.EndpointDto;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EndpointMapper {
    public static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
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
}
