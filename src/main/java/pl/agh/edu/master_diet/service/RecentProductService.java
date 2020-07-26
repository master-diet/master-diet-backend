package pl.agh.edu.master_diet.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.agh.edu.master_diet.core.model.database.Product;
import pl.agh.edu.master_diet.core.model.database.RecentProduct;
import pl.agh.edu.master_diet.core.model.database.User;
import pl.agh.edu.master_diet.core.model.rest.diary.AddRecentProductResponse;
import pl.agh.edu.master_diet.core.model.rest.diary.DeleteRecentProductsResponse;
import pl.agh.edu.master_diet.core.model.rest.diary.MultipleRecentProductsResponse;
import pl.agh.edu.master_diet.core.model.rest.diary.SingleRecentProductResponse;
import pl.agh.edu.master_diet.core.model.shared.RecentProductParameters;
import pl.agh.edu.master_diet.repository.RecentProductRepository;
import pl.agh.edu.master_diet.service.converter.ConversionService;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class RecentProductService {

    private final RecentProductRepository recentProductRepository;
    private final UserService userService;
    private final ProductService productService;
    private final ConversionService conversionService;

    public AddRecentProductResponse addRecentProduct(final RecentProductParameters parameters,
                                                     final Long userId) {
        final User user = userService.getUserById(userId);
        final Product product = productService.getProductById(parameters.getProductId());

        final RecentProduct recentProduct = conversionService.convert(parameters, product, user);
        recentProductRepository.save(recentProduct);

        return AddRecentProductResponse.builder()
                .amount(parameters.getAmount())
                .mealType(parameters.getMealType())
                .portion(parameters.getPortion())
                .portionUnit(parameters.getPortionUnit())
                .build();
    }

    public MultipleRecentProductsResponse getProductsForDate(final LocalDate date,
                                                             final Long userId) {
        final User user = userService.getUserById(userId);

        final List<RecentProduct> recentProducts = recentProductRepository
                .findByUserAndMealTimeDate(user.getId(), date);

        final List<SingleRecentProductResponse> responseList = recentProducts.stream()
                .map(this::createSingleResponseForProduct)
                .collect(toList());

        return MultipleRecentProductsResponse.builder()
                .recentProducts(responseList)
                .build();
    }

    public DeleteRecentProductsResponse deleteRecentProducts(final List<Long> recentProductsIds,
                                                             final Long userId) {
        recentProductsIds.forEach(
                recentProductId -> recentProductRepository.deleteByUserIdAndId(userId, recentProductId));

        return DeleteRecentProductsResponse.builder()
                .recentProductsIds(recentProductsIds)
                .build();
    }

    private SingleRecentProductResponse createSingleResponseForProduct(RecentProduct recentProduct) {
        final Product product = productService.getProductById(recentProduct.getProduct().getId());

        if (!Objects.equals(product.getUnit(), recentProduct.getPortionUnit())) {
            throw new RuntimeException("Unit of product and recent product is not the same");
            // TODO to be handled in the future
        }

        final Float weightEaten = recentProduct.getAmount() * recentProduct.getPortion();
        final Float coefficient = weightEaten / product.getDefaultValue();

        return SingleRecentProductResponse.builder()
                .mealUnit(recentProduct.getPortionUnit())
                .fatEaten(coefficient * product.getFat())
                .caloriesEaten(coefficient * product.getCalories())
                .proteinsEaten(coefficient * product.getProteins())
                .carbohydratesEaten(coefficient * product.getCarbohydrates())
                .mealTime(recentProduct.getMealTime())
                .recentProductId(recentProduct.getId())
                .portion(recentProduct.getPortion())
                .amount(recentProduct.getAmount())
                .mealType(recentProduct.getMealType())
                .productName(product.getName())
                .build();
    }
}
