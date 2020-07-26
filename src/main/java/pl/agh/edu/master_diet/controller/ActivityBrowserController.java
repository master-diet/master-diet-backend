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

@RestController
@RequestMapping("/activity-browser")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ActivityBrowserController {

    private final ActivitySearchService activitySearchService;

    @GetMapping
    public ActivitySearchResponse searchActivity(@RequestParam String searchTerm,
                                                 @RequestParam Integer pageIndex,
                                                 @RequestParam Integer perPage,
                                                 @CurrentUser final UserPrincipal userPrincipal) {
        return activitySearchService.searchActivity(searchTerm, pageIndex, perPage, userPrincipal.getId());
    }

    @GetMapping("/recent-activities")
    public RecentActivityResponse getRecentActivities(@RequestParam Integer pageIndex,
                                                      @RequestParam Integer perPage,
                                                      @CurrentUser final UserPrincipal userPrincipal) {
        return activitySearchService.getRecentActivities(pageIndex, perPage, userPrincipal.getId());
    }
}
