package pl.agh.edu.master_diet.controller;

import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.agh.edu.master_diet.core.model.rest.gamification.PointsRequest;
import pl.agh.edu.master_diet.service.GamificationService;

@RestController
@RequestMapping("/gamification")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class GamificationController {

    private final GamificationService gamificationService;

    @PostMapping
    public void addPoints(@RequestBody PointsRequest request) throws NotFoundException {
        gamificationService.addPointsToUser(request.getUserId(), request.getPoints());
    }
}
