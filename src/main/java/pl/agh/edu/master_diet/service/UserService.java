package pl.agh.edu.master_diet.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import pl.agh.edu.master_diet.core.model.database.User;
import pl.agh.edu.master_diet.exception.ResourceNotFoundException;
import pl.agh.edu.master_diet.repository.UserRepository;

@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserService {


    private final static Double METS_COEFFICIENT = 3.5;
    private final static Integer METS_CALORIC_COEFFICIENT = 200;

    private final UserRepository userRepository;
    private final UserWeightService userWeightService;

    public User getUserById(final Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
    }

    public Integer calculateBurnedCalories(Long userId, Double mets, float time) {
        Double userWeight = userWeightService.getLatestUserWeight(userId).getWeight();
        return (int) (time * mets * METS_COEFFICIENT * userWeight / METS_CALORIC_COEFFICIENT);
    }
}
