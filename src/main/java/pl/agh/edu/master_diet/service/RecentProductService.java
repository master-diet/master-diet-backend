package pl.agh.edu.master_diet.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.agh.edu.master_diet.core.model.database.Product;
import pl.agh.edu.master_diet.core.model.database.RecentProduct;
import pl.agh.edu.master_diet.core.model.database.User;
import pl.agh.edu.master_diet.core.model.rest.diary.AddRecentProductResponse;
import pl.agh.edu.master_diet.core.model.rest.diary.MultipleRecentProductsResponse;
import pl.agh.edu.master_diet.core.model.rest.diary.SingleRecentProductResponse;
import pl.agh.edu.master_diet.core.model.shared.RecentProductParameters;
import pl.agh.edu.master_diet.repository.RecentProductRepository;
import pl.agh.edu.master_diet.service.converter.ConversionService;

import java.time.LocalDate;
import java.util.List;

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

        final List<RecentProduct> recentProductsForDate = recentProductRepository
                .findByUserAndMealTime_Date(user, date);

        final List<SingleRecentProductResponse> recentProductsResponse = recentProductsForDate.stream()
                .map(conversionService::convertProduct)
                .collect(toList());

        return MultipleRecentProductsResponse.builder()
                .recentProducts(recentProductsResponse)
                .build();
    }

}
