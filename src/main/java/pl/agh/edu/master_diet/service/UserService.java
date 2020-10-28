package pl.agh.edu.master_diet.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import pl.agh.edu.master_diet.core.model.database.User;
import pl.agh.edu.master_diet.core.model.database.UserWeight;
import pl.agh.edu.master_diet.core.model.rest.profile.UpdateUserWeightRequest;
import pl.agh.edu.master_diet.core.model.rest.profile.UpdateUserWeightResponse;
import pl.agh.edu.master_diet.core.model.rest.profile.UserProfileResponse;
import pl.agh.edu.master_diet.exception.ResourceNotFoundException;
import pl.agh.edu.master_diet.repository.UserRepository;
import pl.agh.edu.master_diet.repository.UserWeightRepository;

import java.time.LocalDateTime;

@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserService {

    private final static Double METS_COEFFICIENT = 3.5;
    private final static Integer METS_CALORIC_COEFFICIENT = 200;

    private final UserRepository userRepository;
    private final UserPlanService userPlanService;
    private final UserWeightRepository userWeightRepository;
    private final UserWeightService userWeightService;

    public User getUserById(final Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
    }

    public UserProfileResponse getUserProfile(final Long userId) {
        final User user = getUserById(userId);
        final UserWeight userWeight = userWeightRepository.findFirstByUserIdOrderByCreationDateDesc(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User weight", "user_id", userId));
        return UserProfileResponse.builder()
                .user(user)
                .weight(userWeight.getWeight())
                .build();
    }

    public UpdateUserWeightResponse updateUserWeight(final Long userId, final UpdateUserWeightRequest request) {
        final User user = getUserById(userId);
        final Long weightId = userWeightRepository.save(UserWeight.builder()
                .user(user)
                .weight(request.getWeight())
                .creationDate(LocalDateTime.now())
                .build())
                .getId();
        userPlanService.updateWeightInUserPlan(request.getWeight(), userId);

        return new UpdateUserWeightResponse(weightId);
    }

    public Integer calculateBurnedCalories(Long userId, Double mets, float time) {
        User user = getUserById(userId);
        Double userWeight = userWeightService.getLatestUserWeight(user).getWeight();
        return (int) (time * mets * METS_COEFFICIENT * userWeight / METS_CALORIC_COEFFICIENT);
    }
}
