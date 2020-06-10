package pl.agh.edu.master_diet.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.agh.edu.master_diet.service.PingService;

@RestController
@RequestMapping("/")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public final class PingController {

    private final PingService pingService;

    @GetMapping("ping")
    public ResponseEntity<String> ping() {
        return ResponseEntity.ok()
                .body(pingService.createPingResponse());
    }
}
