package pl.agh.edu.master_diet.service;

import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.agh.edu.master_diet.core.model.database.UserGamificationDetail;
import pl.agh.edu.master_diet.repository.UserGamificationDetailRepository;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class GamificationService {

    private final UserGamificationDetailRepository userGamificationDetailRepository;

    public void addPointsToUser(Long userId, Integer points) throws NotFoundException {
        UserGamificationDetail userGamificationDetail = userGamificationDetailRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(String.format("User detail with id %d not found", userId)));
        Integer currentPoints = userGamificationDetail.getPoints();
        Integer newPoints = currentPoints + points;
        userGamificationDetail.setLevel(newPoints);
        userGamificationDetailRepository.save(userGamificationDetail);
    }
}
