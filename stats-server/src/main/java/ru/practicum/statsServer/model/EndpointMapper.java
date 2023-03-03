package ru.practicum.statsServer.model;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import ru.practicum.statsServer.model.dto.EndpointDto;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EndpointMapper {
    public static Endpoint toEndpoint(EndpointDto dto){
        Endpoint endpoint = new Endpoint();
        endpoint.setId(dto.getId());
        endpoint.setIp(dto.getIp());
        endpoint.setTimestamp(dto.getTimestamp());
        endpoint.setUri(dto.getUri());
        endpoint.setApp(dto.getApp());
        return endpoint;
    }

    public static EndpointDto toEndpointDto(Endpoint endpoint){
        return EndpointDto.builder()
                .id(endpoint.getId())
                .app(endpoint.getApp())
                .uri(endpoint.getUri())
                .ip(endpoint.getIp())
                .timestamp(endpoint.getTimestamp())
                .build();
    }
}
