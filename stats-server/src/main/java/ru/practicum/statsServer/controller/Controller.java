package ru.practicum.statsServer.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.statsServer.Service.EndpointService;
import ru.practicum.statsServer.model.EndpointMapper;
import ru.practicum.statsServer.model.dto.EndpointDto;
import ru.practicum.statsServer.model.dto.EndpointDtoOutput;

import javax.validation.constraints.NotBlank;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.List;

import static ru.practicum.statsServer.model.GlobalVariables.FORMAT;

@RestController
@RequiredArgsConstructor
@Validated
@Slf4j
public class Controller {
    private final EndpointService endpointService;

    @PostMapping("/hit")
    public EndpointDto create(@RequestBody EndpointDto endpointDto) {
        log.info("Получен Post запрос на маппинг /hit. EndpointDto - " + endpointDto.toString());
        return EndpointMapper.toEndpointDto(endpointService.create(EndpointMapper.toEndpoint(endpointDto)));
    }

    @GetMapping("/test")
    public String test() {
        return "test";
    }

    @GetMapping("/stats")
    public List<EndpointDtoOutput> getStats(@NotBlank @RequestParam String start,
                                            @NotBlank @RequestParam String end,
                                            @RequestParam(required = false) List<String> uris,
                                            @RequestParam(defaultValue = "false") Boolean unique) {
        log.info("Получен Get запрос на маппинг /stats. Параметры: start - " + start + ", end - " + end
                + ", uris - " + uris + ", уникальность - " + unique);
        List<EndpointDtoOutput> stats;
        stats = endpointService.getStats(
                LocalDateTime.parse(URLDecoder.decode(start, StandardCharsets.UTF_8), FORMAT),
                LocalDateTime.parse(URLDecoder.decode(end, StandardCharsets.UTF_8), FORMAT),
                uris,
                unique);

//        return stats.stream().sorted(Comparator.comparing(EndpointDtoOutput::getHits).reversed())
//                .collect(Collectors.toList());
        return stats;

    }
}
