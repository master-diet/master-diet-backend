package pl.agh.edu.master_diet.service;


import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.agh.edu.master_diet.core.model.database.UserWeight;
import pl.agh.edu.master_diet.repository.UserWeightRepository;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserWeightService {

    private final UserWeightRepository userWeightRepository;

    @SneakyThrows
    public UserWeight getLatestUserWeight(Long userId) {
        return userWeightRepository.findTopByUserIdInOrderByInsertDateDesc(userId).orElseThrow(() -> new NotFoundException("User weight not found"));
    }

}
