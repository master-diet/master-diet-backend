package pl.agh.edu.master_diet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.agh.edu.master_diet.core.model.database.Activity;
import pl.agh.edu.master_diet.core.model.rest.browser.activity.ActivitySearchResponse;
import pl.agh.edu.master_diet.core.model.shared.SearchResult;
import pl.agh.edu.master_diet.repository.ActivityRepository;
import pl.agh.edu.master_diet.service.converter.ConversionService;

import java.util.stream.Collectors;

@Service
public class ActivitySearchService extends SearchService<Activity> {

    private final ConversionService conversionService;

    @Autowired
    public ActivitySearchService(ConversionService conversionService,
                                 ActivityRepository activityRepository,
                                 PageFittingService<Activity> fittingService) {
        super(activityRepository, fittingService);
        this.conversionService = conversionService;
    }

    public ActivitySearchResponse searchActivity(String searchTerm, Integer pageIndex, Integer perPage) {
        SearchResult<Activity> searchResult = searchBrowsable(searchTerm, pageIndex, perPage);
        return ActivitySearchResponse.builder()
                .activities(searchResult.getResult().stream()
                        .map(conversionService::convert)
                        .collect(Collectors.toList()))
                .maximumPageNumber(searchResult.getMaximumPage())
                .build();
    }

}
