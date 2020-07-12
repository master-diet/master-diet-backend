package pl.agh.edu.master_diet.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.agh.edu.master_diet.core.model.database.Product;
import pl.agh.edu.master_diet.core.model.database.RecentProduct;
import pl.agh.edu.master_diet.core.model.database.User;
import pl.agh.edu.master_diet.core.model.rest.diary.AddRecentProductResponse;
import pl.agh.edu.master_diet.core.model.shared.RecentProductParameters;
import pl.agh.edu.master_diet.repository.RecentProductRepository;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class RecentProductService {

    private final RecentProductRepository recentProductRepository;
    private final UserService userService;
    private final ProductService productService;

    public AddRecentProductResponse addRecentProduct(final RecentProductParameters parameters) {
        final User user = userService.getUserById(parameters.getUserId());
        final Product product = productService.getProductById(parameters.getProductId());

        final RecentProduct recentProduct = RecentProduct.builder()
                .amount(parameters.getAmount())
                .mealType(parameters.getMealType())
                .portion(parameters.getPortion())
                .product(product)
                .user(user)
                .portionUnit(parameters.getPortionUnit())
                .build();

        recentProductRepository.save(recentProduct);

        return AddRecentProductResponse.builder()
                .amount(parameters.getAmount())
                .mealType(parameters.getMealType())
                .portion(parameters.getPortion())
                .portionUnit(parameters.getPortionUnit())
                .productId(parameters.getProductId())
                .userId(parameters.getUserId())
                .success(true)
                .build();
    }
}
