package pl.agh.edu.master_diet.service.converter;

import org.springframework.stereotype.Component;
import pl.agh.edu.master_diet.core.model.database.UserActivity;
import pl.agh.edu.master_diet.core.model.rest.browser.activity.BaseActivityInfo;

@Component
public class UserActivityConverter implements GenericConverter<UserActivity, BaseActivityInfo> {
    @Override
    public BaseActivityInfo createFrom(UserActivity dto) {
        return BaseActivityInfo.builder()
                .name(dto.getActivity().getName())
                .burnedCalories(dto.getBurnedCalories())
                .build();
    }
}
