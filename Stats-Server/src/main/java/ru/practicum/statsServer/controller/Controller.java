package ru.practicum.statsServer.controller;

import com.fasterxml.jackson.core.json.UTF8DataInputJsonParser;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.statsServer.Service.EndpointService;
import ru.practicum.statsServer.model.dto.EndpointDto;
import ru.practicum.statsServer.model.EndpointMapper;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Validated
public class Controller {
    public static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private final EndpointService endpointService;

    @PostMapping("/hit")
    public EndpointDto create(@RequestBody EndpointDto endpointDto){
        return EndpointMapper.toEndpointDto(endpointService.create(EndpointMapper.toEndpoint(endpointDto)));
    }
    @GetMapping
    public List<EndpointDto> getStats(@RequestParam String start,
                                       @RequestParam String end,
                                       @RequestParam(required = false) List<String> uris,
                                       @RequestParam(required = false, defaultValue = "false") Boolean unique){
        endpointService.getStats(
                LocalDateTime.parse(URLDecoder.decode(start, StandardCharsets.UTF_8), FORMAT),
                LocalDateTime.parse(URLDecoder.decode(end, StandardCharsets.UTF_8), FORMAT),
                uris,
                unique);
        return null;
    }
}
