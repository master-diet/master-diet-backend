package pl.agh.edu.master_diet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.agh.edu.master_diet.core.model.database.Activity;
import pl.agh.edu.master_diet.core.model.database.UserActivity;
import pl.agh.edu.master_diet.core.model.rest.browser.activity.ActivitySearchResponse;
import pl.agh.edu.master_diet.core.model.rest.browser.activity.RecentActivityResponse;
import pl.agh.edu.master_diet.core.model.shared.SearchResult;
import pl.agh.edu.master_diet.repository.ActivityRepository;
import pl.agh.edu.master_diet.repository.UserActivityRepository;
import pl.agh.edu.master_diet.repository.UserRepository;
import pl.agh.edu.master_diet.service.converter.ConversionService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ActivitySearchService extends SearchService<Activity> {

    private final static Double METS_COEFFICIENT = 3.5;
    private final static Integer METS_CALORIC_COEFFICIENT = 200;

    private final ConversionService conversionService;
    private final UserActivityRepository userActivityRepository;
    private final PageFittingService<UserActivity> userActivityPageFittingService;
    private final UserRepository userRepository;

    @Autowired
    public ActivitySearchService(ConversionService conversionService,
                                 ActivityRepository activityRepository,
                                 PageFittingService<Activity> fittingService,
                                 UserActivityRepository userActivityRepository,
                                 PageFittingService<UserActivity> userActivityPageFittingService,
                                 UserRepository userRepository) {
        super(activityRepository, fittingService);
        this.conversionService = conversionService;
        this.userActivityRepository = userActivityRepository;
        this.userActivityPageFittingService = userActivityPageFittingService;
        this.userRepository = userRepository;
    }

    public ActivitySearchResponse searchActivity(String searchTerm, Integer pageIndex, Integer perPage, Long userId) {
        SearchResult<Activity> searchResult = searchBrowsable(searchTerm, pageIndex, perPage);
        Double userWeight = userRepository.getOne(userId).getUserPlan().getCurrentWeight();
        return ActivitySearchResponse.builder()
                .activities(searchResult.getResult().stream()
                        .map(conversionService::convert)
                        .peek(result -> {
                            Integer burnedCalories = calculateMETS(userWeight, result.getMets());
                            result.setBurnedCalories(burnedCalories);
                        })
                        .collect(Collectors.toList()))
                .maximumPageNumber(searchResult.getMaximumPage())
                .build();
    }

    public RecentActivityResponse getRecentActivities(Integer pageIndex, Integer perPage, Long userId) {
        List<UserActivity> result = userActivityRepository.findByUserId(userId);
        Integer totalNumberOfProducts = result.size();
        result = userActivityPageFittingService.adjustListToPageSize(result, pageIndex, perPage);
        return RecentActivityResponse.builder()
                .activities(result.stream()
                        .map(conversionService::convert)
                        .collect(Collectors.toList()))
                .maximumPageNumber(totalNumberOfProducts)
                .build();
    }

    public Integer calculateMETS(Double userWeight, Double mets) {
        return (int) (mets * METS_COEFFICIENT * userWeight / METS_CALORIC_COEFFICIENT);
    }
}
