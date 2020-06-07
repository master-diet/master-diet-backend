package pl.agh.edu.master_diet.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.agh.edu.master_diet.core.model.database.UserPlan;
import pl.agh.edu.master_diet.core.model.rest.userPlan.UserPlanResponse;
import pl.agh.edu.master_diet.core.model.shared.UserParameters;
import pl.agh.edu.master_diet.repository.UserPlanRepository;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserPlanService {

    private final static Integer WEIGHT_COEFFICIENT = 10;
    private final static Float HEIGHT_COEFFICIENT = 6.25f;
    private final static Integer AGE_COEFFICIENT = 5;


    private final UserPlanRepository userPlanRepository;

    public UserPlanResponse calculateAndSaveUsersPlan(UserParameters userParameters) {
        UserPlan userPlan = calculateUsersPlan(userParameters);
        userPlanRepository.save(userPlan);
        return userPlan.map2UserPlanResponse();
    }

    public UserPlan calculateUsersPlan(UserParameters userParameters) {

        return new UserPlan();
    }
}
