package pl.agh.edu.master_diet.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.agh.edu.master_diet.core.model.rest.user_plan.UserWeightResponse;
import pl.agh.edu.master_diet.security.CurrentUser;
import pl.agh.edu.master_diet.security.UserPrincipal;
import pl.agh.edu.master_diet.service.UserService;
import pl.agh.edu.master_diet.service.converter.ConversionService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/stats")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class StatisticController {

    private final UserService userService;
    private final ConversionService conversionService;

    @GetMapping("/weight")
    public List<UserWeightResponse> getUserWeightList(
            @CurrentUser final UserPrincipal userPrincipal) {
        return userService.getAllUserWeight(userPrincipal.getId())
                .stream()
                .map(conversionService::convert)
                .collect(Collectors.toList());
    }
}
