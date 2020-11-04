package pl.agh.edu.master_diet.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.agh.edu.master_diet.core.model.rest.user_plan.UserParametersRequest;
import pl.agh.edu.master_diet.core.model.rest.user_plan.UserPlanResponse;
import pl.agh.edu.master_diet.core.model.shared.UserParameters;
import pl.agh.edu.master_diet.service.UserPlanService;
import pl.agh.edu.master_diet.service.converter.ConversionService;

import javax.validation.Valid;

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
}
