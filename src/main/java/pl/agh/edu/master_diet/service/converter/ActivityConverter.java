package pl.agh.edu.master_diet.service.converter;

import org.springframework.stereotype.Component;
import pl.agh.edu.master_diet.core.model.database.Activity;
import pl.agh.edu.master_diet.core.model.rest.browser.activity.BaseActivityInfo;

@Component
public class ActivityConverter implements GenericConverter<Activity, BaseActivityInfo> {
    @Override
    public BaseActivityInfo createFrom(Activity dto) {
        return BaseActivityInfo.builder()
                .name(dto.getName())
                .build();
    }
}
