package pl.agh.edu.master_diet.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.agh.edu.master_diet.core.model.rest.browser.activity.ActivitySearchResponse;
import pl.agh.edu.master_diet.core.model.rest.browser.activity.RecentActivityResponse;
import pl.agh.edu.master_diet.security.CurrentUser;
import pl.agh.edu.master_diet.security.UserPrincipal;
import pl.agh.edu.master_diet.service.ActivitySearchService;
import pl.agh.edu.master_diet.service.RecentSearchedService;

@RestController
@RequestMapping("/activity-browser")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ActivityBrowserController {

    private final ActivitySearchService activitySearchService;
    private final RecentSearchedService recentSearchedService;

    @GetMapping
    public ActivitySearchResponse searchProduct(@RequestParam String searchTerm,
                                                @RequestParam Integer pageIndex,
                                                @RequestParam Integer perPage) {
        return activitySearchService.searchActivity(searchTerm, pageIndex, perPage);
    }

    @GetMapping("/recent-activities")
    public RecentActivityResponse getRecentProducts(@RequestParam Integer pageIndex,
                                                    @RequestParam Integer perPage,
                                                    @CurrentUser final UserPrincipal userPrincipal) {
        return recentSearchedService.getRecentActivities(pageIndex, perPage, userPrincipal.getId());
    }
}
