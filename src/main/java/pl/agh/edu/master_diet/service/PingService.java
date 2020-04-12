package pl.agh.edu.master_diet.service;

import org.springframework.stereotype.Service;
import pl.agh.edu.master_diet.core.model.rest.PingResponse;

import java.time.ZonedDateTime;

@Service
public class PingService {

    private static final String PING_RESPONSE_MESSAGE = "Welcome to Master Diet";

    public PingResponse createPingResponse() {
        return PingResponse.builder()
                .message(PING_RESPONSE_MESSAGE)
                .pingTime(ZonedDateTime.now())
                .build();
    }
}
