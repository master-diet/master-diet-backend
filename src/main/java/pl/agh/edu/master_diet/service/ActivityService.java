package pl.agh.edu.master_diet.service;

import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.agh.edu.master_diet.core.model.database.Activity;
import pl.agh.edu.master_diet.repository.ActivityRepository;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ActivityService {
    private final ActivityRepository activityRepository;

    @SneakyThrows
    public Activity getActivityById(final Long productId) {
        return activityRepository.findById(productId)
                .orElseThrow(() -> new NotFoundException("No activity with id: " + productId + " found"));
    }
}
