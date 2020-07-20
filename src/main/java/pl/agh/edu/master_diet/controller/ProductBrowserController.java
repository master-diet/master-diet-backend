package pl.agh.edu.master_diet.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.agh.edu.master_diet.core.model.rest.product_browser.GetRecentProductsResponse;
import pl.agh.edu.master_diet.core.model.rest.product_browser.ProductSearchResponse;
import pl.agh.edu.master_diet.security.CurrentUser;
import pl.agh.edu.master_diet.security.UserPrincipal;
import pl.agh.edu.master_diet.service.SearchService;

@RestController
@RequestMapping("/product-browser")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ProductBrowserController {

    private final SearchService searchService;

    @GetMapping
    public ProductSearchResponse searchProduct(@RequestParam String searchTerm,
                                               @RequestParam Integer pageIndex,
                                               @RequestParam Integer perPage) {
        return searchService.searchProduct(searchTerm, pageIndex, perPage);
    }

    @GetMapping("/recent-products")
    public GetRecentProductsResponse getRecentProducts(@RequestParam Integer pageIndex,
                                                       @RequestParam Integer perPage,
                                                       @CurrentUser final UserPrincipal userPrincipal) {
        return searchService.getRecentProductsResponse(pageIndex, perPage, userPrincipal.getId());
    }
}

