package ru.practicum.http;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.util.DefaultUriBuilderFactory;

@Service
public class BookingClient extends BaseClient {
//    private static final String API_PREFIX = "/bookings";

    @Autowired
    public BookingClient(@Value("${stats-server.url}") String serverUrl, RestTemplateBuilder builder) {
        super(
                builder
                        .uriTemplateHandler(new DefaultUriBuilderFactory(serverUrl))
                        .requestFactory(HttpComponentsClientHttpRequestFactory::new)
                        .build()
        );
    }
//
//    public ResponseEntity<Object> getBookings(long userId, String state, Integer from, Integer size) {
//        return get("?state=" + state + "&from=" + from + "&size=" + size, userId);
//    }
//
//
////    public ResponseEntity<Object> bookItem(long userId, BookItemRequestDto requestDto) {
////        return post("", userId, requestDto);
////    }
//
//    public ResponseEntity<Object> getBooking(long userId, Long bookingId) {
//        return get("/" + bookingId, userId);
//    }
//
//    public ResponseEntity<Object> update(long userId, long bookingId, boolean approved) {
//        return patch("/" + bookingId + "?approved=" + approved, userId);
//    }
//
//    public ResponseEntity<Object> getAllByOwner(Long userId, String state, Integer from, Integer size) {
//        return get("/owner?state=" + state + "&from=" + from + "&size=" + size, userId);
//    }
}
