package pl.agh.edu.master_diet.service.converter;

import org.springframework.stereotype.Component;
import pl.agh.edu.master_diet.core.model.database.Activity;
import pl.agh.edu.master_diet.core.model.database.User;
import pl.agh.edu.master_diet.core.model.database.UserActivity;
import pl.agh.edu.master_diet.core.model.rest.browser.activity.BaseActivityInfo;
import pl.agh.edu.master_diet.core.model.rest.diary.AddUserActivityRequest;
import pl.agh.edu.master_diet.core.model.shared.UserActivityParameters;

@Component
public class UserActivityConverter
        implements GenericConverter<UserActivity, BaseActivityInfo> {

    @Override
    public BaseActivityInfo createFrom(UserActivity dto) {
        return BaseActivityInfo.builder()
                .id(dto.getActivity().getId())
                .name(dto.getActivity().getName())
                .burnedCalories(dto.getBurnedCalories())
                .build();
    }

    public UserActivityParameters createFrom(final AddUserActivityRequest request) {
        return UserActivityParameters.builder()
                .amount(request.getAmount())
                .activityTime(request.getActivityTime())
                .activityId(request.getActivityId())
                .time(request.getTime())
                .timeUnit(request.getTimeUnit())
                .build();
    }

    public UserActivity createFrom(final UserActivityParameters parameters,
                                   final Activity activity,
                                   final User user) {
        return UserActivity.builder()
                .auditDate(parameters.getActivityTime())
                .activity(activity)
                .user(user)
                .build();
    }
}
