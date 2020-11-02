package pl.agh.edu.master_diet.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.agh.edu.master_diet.core.model.database.User;
import pl.agh.edu.master_diet.core.model.rest.profile.UpdateUserWeightRequest;
import pl.agh.edu.master_diet.core.model.rest.profile.UpdateUserWeightResponse;
import pl.agh.edu.master_diet.core.model.rest.profile.UserProfileResponse;
import pl.agh.edu.master_diet.core.model.rest.user_plan.UserCaloriesStatusResponse;
import pl.agh.edu.master_diet.security.CurrentUser;
import pl.agh.edu.master_diet.security.UserPrincipal;
import pl.agh.edu.master_diet.service.UserService;

import java.time.LocalDate;

@RestController
@RequestMapping("/user")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {

    private final UserService userService;

    @GetMapping("/me")
    @PreAuthorize("hasRole('USER')")
    public User getCurrentUser(@CurrentUser final UserPrincipal userPrincipal) {
        return userService.getUserById(userPrincipal.getId());
    }

    @GetMapping("/profile")
    @PreAuthorize("hasRole('USER')")
    public UserProfileResponse getUserProfile(@CurrentUser final UserPrincipal userPrincipal) {
        return userService.getUserProfile(userPrincipal.getId());
    }

    @PutMapping("/weight")
    @PreAuthorize("hasRole('USER')")
    public UpdateUserWeightResponse updateUserWeight(@CurrentUser final UserPrincipal userPrincipal,
                                                     @RequestBody final UpdateUserWeightRequest request) {
        return userService.updateUserWeight(userPrincipal.getId(), request);
    }

    @GetMapping("/caloriesStatus")
    @PreAuthorize("hasRole('USER')")
    public UserCaloriesStatusResponse getUserCaloriesStatus(
            @CurrentUser final UserPrincipal userPrincipal,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        if (date == null) {
            date = LocalDate.now();
        }
        return userService.getUserCaloriesStatus(date, userPrincipal.getId());
    }
}
