package pl.agh.edu.master_diet.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import pl.agh.edu.master_diet.core.model.database.User;
import pl.agh.edu.master_diet.core.model.database.UserActivity;
import pl.agh.edu.master_diet.core.model.database.UserWeight;
import pl.agh.edu.master_diet.core.model.rest.profile.UpdateUserWeightRequest;
import pl.agh.edu.master_diet.core.model.rest.profile.UpdateUserWeightResponse;
import pl.agh.edu.master_diet.core.model.rest.profile.UserProfileResponse;
import pl.agh.edu.master_diet.core.model.rest.user_plan.UserCaloriesStatusResponse;
import pl.agh.edu.master_diet.exception.ResourceNotFoundException;
import pl.agh.edu.master_diet.repository.RecentProductRepository;
import pl.agh.edu.master_diet.repository.UserActivityRepository;
import pl.agh.edu.master_diet.repository.UserRepository;
import pl.agh.edu.master_diet.repository.UserWeightRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserService {

    private final static Double METS_COEFFICIENT = 3.5;
    private final static Integer METS_CALORIC_COEFFICIENT = 200;

    private final UserRepository userRepository;
    private final UserPlanService userPlanService;
    private final UserWeightRepository userWeightRepository;
    private final UserWeightService userWeightService;
    private final RecentProductRepository recentProductRepository;
    private final UserActivityRepository userActivityRepository;

    public User getUserById(final Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
    }

    public UserProfileResponse getUserProfile(final Long userId) {
        final User user = getUserById(userId);
        final UserWeight userWeight = userWeightRepository.findFirstByUserOrderByCreationDateDesc(user)
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

    public UserCaloriesStatusResponse getUserCaloriesStatus(final LocalDate date, final Long userId) {
        User user = userRepository.getOne(userId);
        Integer dailyCaloricDemand = userPlanService.getUserPlan(user).getCalories();
        Integer caloriesConsumed = recentProductRepository.getCaloriesConsumed(userId, date)
                .orElse(0);
        Integer caloriesBurned = calculateBurnedCaloriesForDate(date, userId);
        return UserCaloriesStatusResponse.builder()
                .dailyCaloricDemand(dailyCaloricDemand)
                .caloriesConsumed(caloriesConsumed - caloriesBurned)
                .build();
    }

    public List<UserWeight> getAllUserWeight(Long userId) {
        User user = getUserById(userId);
        return userWeightRepository.findAllByUserOrderByCreationDate(user);
    }

    private Integer calculateBurnedCaloriesForDate(final LocalDate date, final Long userId) {
        final List<UserActivity> userActivities = userActivityRepository
                .findByUserIdAndAuditDate(userId, date);
        return userActivities.stream()
                .mapToInt(UserActivity::getBurnedCalories)
                .sum();
    }
}
