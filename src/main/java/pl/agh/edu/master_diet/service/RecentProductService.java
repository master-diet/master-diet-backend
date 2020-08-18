package pl.agh.edu.master_diet.service;

import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.agh.edu.master_diet.core.model.database.Product;
import pl.agh.edu.master_diet.core.model.database.RecentProduct;
import pl.agh.edu.master_diet.core.model.database.User;
import pl.agh.edu.master_diet.core.model.rest.diary.MultipleRecentProductsResponse;
import pl.agh.edu.master_diet.core.model.rest.diary.SingleRecentProductInfo;
import pl.agh.edu.master_diet.core.model.shared.RecentProductParameters;
import pl.agh.edu.master_diet.core.model.standard.StandardApiResponse;
import pl.agh.edu.master_diet.exception.DeleteException;
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

    public StandardApiResponse addRecentProduct(final RecentProductParameters parameters,
                                                final Long userId) {
        final User user = userService.getUserById(userId);
        final Product product = productService.getProductById(parameters.getProductId());

        final RecentProduct recentProduct = conversionService.convert(parameters, product, user);
        recentProductRepository.save(recentProduct);

        return StandardApiResponse.builder()
                .success(true)
                .message("Product " + product.getName() + " has been successfully added")
                .build();
    }

    public MultipleRecentProductsResponse getProductsForDate(final LocalDate date,
                                                             final Long userId) {
        final User user = userService.getUserById(userId);

        final List<RecentProduct> recentProducts = recentProductRepository
                .findByUserAndMealTimeDate(user.getId(), date);

        final List<SingleRecentProductInfo> responseList = recentProducts.stream()
                .map(this::createSingleResponseForProduct)
                .collect(toList());

        return MultipleRecentProductsResponse.builder()
                .recentProducts(responseList)
                .build();
    }

    public StandardApiResponse deleteRecentProducts(final List<Long> recentProductsIds,
                                                    final Long userId) {
        recentProductsIds.stream()
                .filter(recentProductId -> recentProductRepository.deleteByUserIdAndId(userId, recentProductId) == 0)
                .forEach(recentProductId -> {
                    throw new DeleteException("Something went wrong while deleting");
                });

        return StandardApiResponse.builder()
                .success(true)
                .message("Products " + StringUtils.join(recentProductsIds, ", ")
                        + " have been successfully deleted")
                .build();
    }

    private SingleRecentProductInfo createSingleResponseForProduct(RecentProduct recentProduct) {
        final Product product = productService.getProductById(recentProduct.getProduct().getId());

        if (!Objects.equals(product.getUnit(), recentProduct.getPortionUnit())) {
            throw new RuntimeException("Unit of product and recent product is not the same");
            // TODO to be handled in the future
        }

        final Float weightEaten = recentProduct.getAmount() * recentProduct.getPortion();
        final Float coefficient = weightEaten / product.getDefaultValue();

        return SingleRecentProductInfo.builder()
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
