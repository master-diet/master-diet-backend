package pl.agh.edu.master_diet.service;

import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.agh.edu.master_diet.core.model.database.Product;
import pl.agh.edu.master_diet.core.model.database.RecentProduct;
import pl.agh.edu.master_diet.core.model.database.User;
import pl.agh.edu.master_diet.core.model.database.UserPlan;
import pl.agh.edu.master_diet.core.model.rest.diary.MultipleRecentProductsInfo;
import pl.agh.edu.master_diet.core.model.rest.diary.MultipleRecentProductsResponse;
import pl.agh.edu.master_diet.core.model.rest.diary.SimpleSummaryRestProductInfo;
import pl.agh.edu.master_diet.core.model.rest.diary.SingleRecentProductInfo;
import pl.agh.edu.master_diet.core.model.rest.diary.demand.DemandInfo;
import pl.agh.edu.master_diet.core.model.shared.RecentProductParameters;
import pl.agh.edu.master_diet.core.model.standard.StandardApiResponse;
import pl.agh.edu.master_diet.exception.DeleteException;
import pl.agh.edu.master_diet.repository.RecentProductRepository;
import pl.agh.edu.master_diet.service.converter.ConversionService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class RecentProductService {

    private final RecentProductRepository recentProductRepository;
    private final UserService userService;
    private final ProductService productService;
    private final UserPlanService userPlanService;
    private final ConversionService conversionService;
    private final AchievementService achievementService;

    public StandardApiResponse addRecentProduct(final RecentProductParameters parameters,
                                                final Long userId) {
        final User user = userService.getUserById(userId);
        final Product product = productService.getProductById(parameters.getProductId());

        final RecentProduct recentProduct = conversionService.convert(parameters, product, user);
        recentProductRepository.save(recentProduct);
        achievementService.updateProductAchievementStatus(userId, parameters);

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

        final List<SingleRecentProductInfo> responseList = new ArrayList<>();
        final MultipleRecentProductsInfo summarizedInfo = MultipleRecentProductsInfo.createEmpty();
        final UserPlan userPlan = userPlanService.getUserPlan(user);

        for (RecentProduct recentProduct : recentProducts) {
            SingleRecentProductInfo singleInfo = recentProduct.createSingleResponseForProduct();
            summarizedInfo.updateValues(singleInfo);
            responseList.add(singleInfo);
        }

        return MultipleRecentProductsResponse.builder()
                .summaryList(SimpleSummaryRestProductInfo
                        .fromNutrientInfoList(
                                userPlan,
                                List.of(
                                        summarizedInfo.getCaloriesInfo(),
                                        summarizedInfo.getProteinsInfo(),
                                        summarizedInfo.getFatInfo(),
                                        summarizedInfo.getCarbohydratesInfo())))
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

    private DemandInfo createDemandInfo(final UserPlan userPlan,
                                        final MultipleRecentProductsInfo summarizedInfo) {

//        return DemandInfo.builder()
//                .fat(FatInfo.builder()
//                        // TODO: difference calculation for all of infos
//                        .sum(userPlan.getFat())
//                        .build())
//                .calories(CaloriesInfo.builder()
//                        .sum(userPlan.getCalories())
//                        .build())
//                .proteins(ProteinsInfo.builder()
//                        .sum(userPlan.getProteins())
//                        .build())
//                .carbohydrates(CarbohydratesInfo.builder()
//                        .sum(userPlan.getCarbohydrates())
//                        .build())
//                .build();
        return null;
    }
}
