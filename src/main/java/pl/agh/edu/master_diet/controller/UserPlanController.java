package pl.agh.edu.master_diet.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.agh.edu.master_diet.core.model.rest.user_plan.UserCaloriesStatusResponse;
import pl.agh.edu.master_diet.core.model.rest.user_plan.UserParametersRequest;
import pl.agh.edu.master_diet.core.model.rest.user_plan.UserPlanResponse;
import pl.agh.edu.master_diet.core.model.shared.UserParameters;
import pl.agh.edu.master_diet.security.CurrentUser;
import pl.agh.edu.master_diet.security.UserPrincipal;
import pl.agh.edu.master_diet.service.UserPlanService;
import pl.agh.edu.master_diet.service.converter.ConversionService;

import javax.validation.Valid;
import java.time.LocalDate;

@RestController
@RequestMapping("/plan")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserPlanController {

    private final ConversionService conversionService;
    private final UserPlanService userPlanService;

    @PostMapping
    public UserPlanResponse calculateUserPlan(@Valid @RequestBody UserParametersRequest request) {
        UserParameters userParameters = conversionService.convert(request);
        return userPlanService.calculateAndSaveUsersPlan(userParameters);
    }

    @GetMapping
    public UserCaloriesStatusResponse getUserCaloriesStatus(
            @CurrentUser final UserPrincipal userPrincipal,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        if (date == null) {
            date = LocalDate.now();
        }
        return userPlanService.getUserCaloriesStatus(date, userPrincipal.getId());
    }
}
