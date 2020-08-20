package pl.agh.edu.master_diet.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.agh.edu.master_diet.core.model.database.User;
import pl.agh.edu.master_diet.core.model.rest.profile.UserProfileResponse;
import pl.agh.edu.master_diet.security.CurrentUser;
import pl.agh.edu.master_diet.security.UserPrincipal;
import pl.agh.edu.master_diet.service.UserService;

@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {

    private final UserService userService;

    @GetMapping("/user/me")
    @PreAuthorize("hasRole('USER')")
    public User getCurrentUser(@CurrentUser final UserPrincipal userPrincipal) {
        return userService.getUserById(userPrincipal.getId());
    }

    @GetMapping("/user/profile")
    @PreAuthorize("hasRole('USER')")
    public UserProfileResponse getUserProfile(@CurrentUser final UserPrincipal userPrincipal) {
        return userService.getUserProfile(userPrincipal.getId());
    }
}
