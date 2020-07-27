package pl.agh.edu.master_diet.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.agh.edu.master_diet.core.model.rest.AchievementSetResponse;
import pl.agh.edu.master_diet.security.CurrentUser;
import pl.agh.edu.master_diet.security.UserPrincipal;
import pl.agh.edu.master_diet.service.AchievementService;

@RestController
@RequestMapping("/achievements")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AchievementsController {

    private final AchievementService achievementService;

    @GetMapping
    public AchievementSetResponse getUserAchievements(@CurrentUser final UserPrincipal userPrincipal) {
        return achievementService.getUserAchievements(userPrincipal.getId());
    }
}
