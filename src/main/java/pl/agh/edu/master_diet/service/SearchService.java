package pl.agh.edu.master_diet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.agh.edu.master_diet.core.model.database.Product;
import pl.agh.edu.master_diet.core.model.database.RecentProduct;
import pl.agh.edu.master_diet.core.model.rest.product_browser.GetRecentProductsResponse;
import pl.agh.edu.master_diet.core.model.rest.product_browser.ProductSearchResponse;
import pl.agh.edu.master_diet.repository.ProductBrowserRepository;
import pl.agh.edu.master_diet.repository.RecentProductsRepository;
import pl.agh.edu.master_diet.service.converter.ConversionService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SearchService {

    private final ConversionService conversionService;
    private final BrowserService<Product> productBrowserService;
    @Autowired
    private PageFittingService<RecentProduct> recentProductPageFittingService;
    @Autowired
    private PageFittingService<Product> productPageFittingService;
    @Autowired
    private RecentProductsRepository recentProductsRepository;

    public SearchService(ConversionService conversionService, ProductBrowserRepository productBrowserRepository) {
        this.conversionService = conversionService;
        this.productBrowserService = new BrowserService<>(productBrowserRepository);
    }


    public ProductSearchResponse searchProduct(String searchTerm, Integer pageIndex, Integer perPage) {
        List<Product> searchedProducts = productBrowserService.search(searchTerm);
        Integer maximumPageNumber = productPageFittingService.calculateMaximumPageNumber(searchedProducts, perPage);
        searchedProducts = productPageFittingService.adjustListToPageInfo(searchedProducts, pageIndex, perPage);
        return ProductSearchResponse.builder()
                .products(searchedProducts.stream()
                        .map(conversionService::convert)
                        .collect(Collectors.toList()))
                .maximumPageNumber(maximumPageNumber)
                .build();
    }

    public GetRecentProductsResponse getRecentProductsResponse(Integer pageIndex, Integer perPage, Long userId) {
        List<RecentProduct> result = recentProductsRepository.findByUserId(userId);
        Integer maximumPageNumber = recentProductPageFittingService.calculateMaximumPageNumber(result, perPage);
        result = recentProductPageFittingService.adjustListToPageInfo(result, pageIndex, perPage);

        return GetRecentProductsResponse.builder()
                .products(result.stream()
                        .map(conversionService::convert)
                        .collect(Collectors.toList()))
                .maximumPageNumber(maximumPageNumber)
                .build();
    }

}
