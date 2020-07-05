package pl.agh.edu.master_diet.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.agh.edu.master_diet.core.model.database.User;
import pl.agh.edu.master_diet.core.model.rest.diary.AddRecentProductResponse;
import pl.agh.edu.master_diet.core.model.shared.RecentProductParameters;
import pl.agh.edu.master_diet.repository.RecentProductRepository;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class RecentProductService {

    private final RecentProductRepository recentProductRepository;
    private final UserService userService;

    public AddRecentProductResponse addRecentProduct(final RecentProductParameters parameters) {
        final User user = userService.getUserById(parameters.getUserId());

        // TODO
        return null;
    }
}
