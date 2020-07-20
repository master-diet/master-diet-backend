package pl.agh.edu.master_diet.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.agh.edu.master_diet.core.model.database.RecentProduct;
import pl.agh.edu.master_diet.core.model.database.UserActivity;
import pl.agh.edu.master_diet.core.model.rest.browser.activity.RecentActivityResponse;
import pl.agh.edu.master_diet.core.model.rest.browser.product.RecentProductsResponse;
import pl.agh.edu.master_diet.repository.RecentProductsRepository;
import pl.agh.edu.master_diet.repository.UserActivityRepository;
import pl.agh.edu.master_diet.service.converter.ConversionService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class RecentSearchedService {

    private final ConversionService conversionService;
    private final UserActivityRepository userActivityRepository;
    private final PageFittingService<UserActivity> userActivityPageFittingService;
    private final RecentProductsRepository recentProductsRepository;
    private final PageFittingService<RecentProduct> recentProductPageFittingService;

    public RecentActivityResponse getRecentActivities(Integer pageIndex, Integer perPage, Long userId) {
        List<UserActivity> result = userActivityRepository.findByUserId(userId);
        Integer maximumPageNumber = userActivityPageFittingService.calculateMaximumPageNumber(result, perPage);
        result = userActivityPageFittingService.adjustListToPageInfo(result, pageIndex, perPage);

        return RecentActivityResponse.builder()
                .activityInfos(result.stream()
                        .map(conversionService::convert)
                        .collect(Collectors.toList()))
                .maximumPageNumber(maximumPageNumber)
                .build();
    }

    public RecentProductsResponse getRecentProducts(Integer pageIndex, Integer perPage, Long userId) {
        List<RecentProduct> result = recentProductsRepository.findByUserId(userId);
        Integer maximumPageNumber = recentProductPageFittingService.calculateMaximumPageNumber(result, perPage);
        result = recentProductPageFittingService.adjustListToPageInfo(result, pageIndex, perPage);

        return RecentProductsResponse.builder()
                .products(result.stream()
                        .map(conversionService::convert)
                        .collect(Collectors.toList()))
                .maximumPageNumber(maximumPageNumber)
                .build();
    }
}
