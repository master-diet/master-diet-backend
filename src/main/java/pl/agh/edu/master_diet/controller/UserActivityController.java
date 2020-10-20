package pl.agh.edu.master_diet.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.agh.edu.master_diet.core.model.rest.diary.AddUserActivityRequest;
import pl.agh.edu.master_diet.core.model.rest.diary.DeleteUserActivitiesRequest;
import pl.agh.edu.master_diet.core.model.rest.diary.MultipleUserActivityResponse;
import pl.agh.edu.master_diet.core.model.shared.UserActivityParameters;
import pl.agh.edu.master_diet.core.model.standard.StandardApiResponse;
import pl.agh.edu.master_diet.security.CurrentUser;
import pl.agh.edu.master_diet.security.UserPrincipal;
import pl.agh.edu.master_diet.service.UserActivityService;
import pl.agh.edu.master_diet.service.converter.ConversionService;

import javax.validation.Valid;
import java.time.LocalDate;

@Slf4j
@RestController
@RequestMapping("/userActivities")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserActivityController {

    private final ConversionService conversionService;
    private final UserActivityService userActivityService;

    @PostMapping("/add")
    public ResponseEntity<StandardApiResponse> addRecentProduct(
            @Valid @RequestBody final AddUserActivityRequest request,
            @CurrentUser final UserPrincipal userPrincipal) {

        log.info("Attempt to add user activity");
        final UserActivityParameters userActivityParameters = conversionService.convert(request);
        return ResponseEntity.ok()
                .body(userActivityService.addUserActivity(userActivityParameters, userPrincipal.getId()));
    }

    @GetMapping("/getForDate")
    public ResponseEntity<MultipleUserActivityResponse> getRecentProductsForDate(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            @CurrentUser final UserPrincipal userPrincipal) {

        log.info("Attempt to retrieve user activity for date: " + date);
        return ResponseEntity.ok()
                .body(userActivityService.getUserActivityForDate(date, userPrincipal.getId()));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<StandardApiResponse> deleteRecentProducts(
            @Valid @RequestBody final DeleteUserActivitiesRequest request,
            @CurrentUser final UserPrincipal userPrincipal) {

        log.info("Attempt to delete recent products");
        return ResponseEntity.ok()
                .body(userActivityService
                        .deleteUserActivities(request.getUserActivitiesIds(), userPrincipal.getId()));
    }
}
