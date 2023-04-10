package ru.practicum.http;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

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

    public EndpointDtoOutput get(String request) {
                return client.get()
                .uri("http://localhost:9090" + request)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .exchange()
                .flatMap(clientResponse -> clientResponse.bodyToMono(EndpointDtoOutput.class))
                .block();
        }
}
