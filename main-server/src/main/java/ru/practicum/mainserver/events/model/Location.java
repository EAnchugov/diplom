package ru.practicum.mainserver.events.model;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
public class Location {
    private float lat;
    private float lon;
}
