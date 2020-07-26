package pl.agh.edu.master_diet.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.agh.edu.master_diet.core.model.rest.diary.*;
import pl.agh.edu.master_diet.core.model.shared.RecentProductParameters;
import pl.agh.edu.master_diet.security.CurrentUser;
import pl.agh.edu.master_diet.security.UserPrincipal;
import pl.agh.edu.master_diet.service.RecentProductService;
import pl.agh.edu.master_diet.service.converter.ConversionService;

import javax.validation.Valid;
import java.time.LocalDate;

@Slf4j
@RestController
@RequestMapping("/diary")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class RecentProductController {

    private final ConversionService conversionService;
    private final RecentProductService recentProductService;

    @PostMapping("/add")
    public ResponseEntity<AddRecentProductResponse> addRecentProduct(
            @Valid @RequestBody final AddRecentProductRequest request,
            @CurrentUser final UserPrincipal userPrincipal) {

        log.info("Attempt to add recent product");
        final RecentProductParameters recentProductParameters = conversionService.convert(request);
        return ResponseEntity.ok()
                .body(recentProductService.addRecentProduct(recentProductParameters, userPrincipal.getId()));
    }

    @GetMapping("/getForDate")
    public ResponseEntity<MultipleRecentProductsResponse> getRecentProductsForDate(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            @CurrentUser final UserPrincipal userPrincipal) {

        log.info("Attempt to retrieve recent products for date: " + date);
        return ResponseEntity.ok()
                .body(recentProductService.getProductsForDate(date, userPrincipal.getId()));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<DeleteRecentProductsResponse> deleteRecentProducts(
            @Valid @RequestBody final DeleteRecentProductsRequest request,
            @CurrentUser final UserPrincipal userPrincipal) {

        log.info("Attempt to delete recent products");
        return ResponseEntity.ok()
                .body(recentProductService
                        .deleteRecentProducts(request.getRecentProductsIds(), userPrincipal.getId()));
    }
}
