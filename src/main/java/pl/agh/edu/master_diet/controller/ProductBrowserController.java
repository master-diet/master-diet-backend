package pl.agh.edu.master_diet.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.agh.edu.master_diet.core.model.rest.product_browser.ProductSearchResponse;
import pl.agh.edu.master_diet.service.ProductBrowserService;

@RestController
@RequestMapping("/product-browser")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ProductBrowserController {

    private final ProductBrowserService productBrowserService;

    @GetMapping
    public ProductSearchResponse searchProduct(@RequestParam String searchTerm) {
        return productBrowserService.searchProducts(searchTerm);
    }
}

