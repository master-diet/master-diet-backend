package pl.agh.edu.master_diet.service;

import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.agh.edu.master_diet.core.model.database.Activity;
import pl.agh.edu.master_diet.core.model.database.User;
import pl.agh.edu.master_diet.core.model.database.UserActivity;
import pl.agh.edu.master_diet.core.model.rest.diary.MultipleUserActivityResponse;
import pl.agh.edu.master_diet.core.model.rest.diary.SingleUserActivityInfo;
import pl.agh.edu.master_diet.core.model.shared.UserActivityParameters;
import pl.agh.edu.master_diet.core.model.standard.StandardApiResponse;
import pl.agh.edu.master_diet.exception.DeleteException;
import pl.agh.edu.master_diet.repository.UserActivityRepository;
import pl.agh.edu.master_diet.service.converter.ConversionService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserActivityService {

    private final UserActivityRepository userActivityRepository;
    private final UserService userService;
    private final ActivityService productService;
    private final ConversionService conversionService;

    public StandardApiResponse addUserActivity(UserActivityParameters parameters, Long userId) {
        final User user = userService.getUserById(userId);
        final Activity activity = productService.getActivityById(parameters.getActivityId());

        final UserActivity userActivity = conversionService.convert(parameters, activity, user);
        float time = parameters.getTime() * parameters.getAmount();
        userService.calculateBurnedCalories(userId, activity.getMets(), time);
        userActivityRepository.save(userActivity);

        return StandardApiResponse.builder()
                .success(true)
                .message("Activity " + activity.getName() + " has been successfully added")
                .build();
    }

    public MultipleUserActivityResponse getUserActivityForDate(LocalDate date, Long userId) {
        final User user = userService.getUserById(userId);
        final List<UserActivity> userActivities = userActivityRepository
                .findByUserIdAndAuditDate(user.getId(), date);
        final List<SingleUserActivityInfo> responseList = new ArrayList<>();
        Integer burnedCaloriesSum = 0;
        for (UserActivity userActivity : userActivities) {
            burnedCaloriesSum += userActivity.getBurnedCalories();
            SingleUserActivityInfo singleInfo = userActivity.createSingleResponseForActivity();
            responseList.add(singleInfo);
        }

        return MultipleUserActivityResponse.builder()
                .burnedCaloriesSum(burnedCaloriesSum)
                .infoList(responseList)
                .build();
    }


    public StandardApiResponse deleteUserActivities(List<Long> userActivitiesIds, Long userId) {
        userActivitiesIds.stream()
                .filter(recentProductId -> userActivityRepository.deleteByUserIdAndId(userId, recentProductId) == 0)
                .forEach(recentProductId -> {
                    throw new DeleteException("Something went wrong while deleting");
                });

        return StandardApiResponse.builder()
                .success(true)
                .message("Activities " + StringUtils.join(userActivitiesIds, ", ")
                        + " have been successfully deleted")
                .build();
    }
}
