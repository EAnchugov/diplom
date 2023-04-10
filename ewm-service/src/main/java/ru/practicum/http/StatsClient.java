package ru.practicum.http;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
public class StatsClient {

    private WebClient client = WebClient.create();

    private ObjectMapper objectMapper = new ObjectMapper();

    public EndpointDtoOutput hit(EndpointDto endpointDto) {
        Mono<EndpointDtoOutput> endpoint = client.post()
                .uri("http://localhost:9090/hit")
                .body(Mono.just(endpointDto), EndpointDto.class)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .retrieve()
                .bodyToMono(EndpointDtoOutput.class);
        return endpoint.block();
    }

    public List<EndpointDtoOutput> get(String start, String end, List<String> uris, Boolean unique) {
        Mono<List<EndpointDtoOutput>> response = client.get()
                .uri("http://localhost:9090" + "/stats?start=" + start +
                        "&end=" + end + "&uris=" + uris + "&unique=" + unique)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<EndpointDtoOutput>>() {});
        List<EndpointDtoOutput> readers = response.block();
        return readers;
        }
}
