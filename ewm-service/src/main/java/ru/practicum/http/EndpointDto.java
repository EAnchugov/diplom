package ru.practicum.http;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Size;

@Getter
@Setter
@Builder
public class EndpointDto {
    @Size(min = 1,max = 100,message = "Incorrect app length. Should be longer than 1, but shorter than 100")
    private String app;
    @Size(min = 1,max = 100,message = "Incorrect uri length. Should be longer than 1, but shorter than 100")
    private String uri;
    @Size(min = 7,max = 15,message = "Incorrect IP length")
    private String ip;
    private String timestamp;
}
