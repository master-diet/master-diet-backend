package pl.agh.edu.master_diet.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.agh.edu.master_diet.core.model.rest.browser.product.ProductSearchResponse;
import pl.agh.edu.master_diet.core.model.rest.browser.product.RecentProductsResponse;
import pl.agh.edu.master_diet.security.CurrentUser;
import pl.agh.edu.master_diet.security.UserPrincipal;
import pl.agh.edu.master_diet.service.ProductSearchService;
import pl.agh.edu.master_diet.service.RecentSearchedService;

@RestController
@RequestMapping("/product-browser")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ProductBrowserController {

    private final ProductSearchService productSearchService;
    private final RecentSearchedService recentSearchedService;

    @GetMapping
    public ProductSearchResponse searchProduct(@RequestParam String searchTerm,
                                               @RequestParam Integer pageIndex,
                                               @RequestParam Integer perPage) {
        return productSearchService.searchProduct(searchTerm, pageIndex, perPage);
    }

    @GetMapping("/recent-products")
    public RecentProductsResponse getRecentProducts(@RequestParam Integer pageIndex,
                                                    @RequestParam Integer perPage,
                                                    @CurrentUser final UserPrincipal userPrincipal) {
        return recentSearchedService.getRecentProducts(pageIndex, perPage, userPrincipal.getId());
    }
}

