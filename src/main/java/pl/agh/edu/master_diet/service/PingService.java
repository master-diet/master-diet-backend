package pl.agh.edu.master_diet.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.agh.edu.master_diet.repository.InfoRepository;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.Properties;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PingService {

    private static final String PING_RESPONSE_MESSAGE = "Welcome to Master Diet";
    private final InfoRepository infoRepository;

    public String createPingResponse() {
        return "<pre>" + PING_RESPONSE_MESSAGE + "<br>\n"
                + "App time:       " + ZonedDateTime.now() + "<br>\n"
                + "Database time:  " + infoRepository.getSysdate() + "<br></pre>\n";
    }
}
