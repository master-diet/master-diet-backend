package pl.agh.edu.master_diet.service;

import org.springframework.stereotype.Service;
import pl.agh.edu.master_diet.core.model.database.Product;
import pl.agh.edu.master_diet.core.model.database.RecentProduct;
import pl.agh.edu.master_diet.core.model.rest.browser.product.ProductSearchResponse;
import pl.agh.edu.master_diet.core.model.rest.browser.product.RecentProductsResponse;
import pl.agh.edu.master_diet.core.model.shared.SearchResult;
import pl.agh.edu.master_diet.repository.ProductRepository;
import pl.agh.edu.master_diet.repository.RecentProductsRepository;
import pl.agh.edu.master_diet.service.converter.ConversionService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductSearchService extends SearchService<Product> {

    private final ConversionService conversionService;
    private final RecentProductsRepository recentProductsRepository;
    private final PageFittingService<RecentProduct> recentProductPageFittingService;

    public ProductSearchService(ConversionService conversionService,
                                ProductRepository browserRepository,
                                PageFittingService<Product> fittingService,
                                RecentProductsRepository recentProductsRepository,
                                PageFittingService<RecentProduct> recentProductPageFittingService) {
        super(browserRepository, fittingService);
        this.conversionService = conversionService;
        this.recentProductsRepository = recentProductsRepository;
        this.recentProductPageFittingService = recentProductPageFittingService;
    }

    public ProductSearchResponse searchProduct(String searchTerm, Integer pageIndex, Integer perPage) {
        SearchResult<Product> searchResult = searchBrowsable(searchTerm, pageIndex, perPage);
        return ProductSearchResponse.builder()
                .products(searchResult.getResult().stream()
                        .map(conversionService::convert)
                        .collect(Collectors.toList()))
                .maximumPageNumber(searchResult.getMaximumPage())
                .build();
    }

    public RecentProductsResponse getRecentProducts(Integer pageIndex, Integer perPage, Long userId) {
        List<RecentProduct> result = recentProductsRepository.findByUserId(userId);
        Integer maximumPageNumber = recentProductPageFittingService.calculateMaximumPageNumber(result, perPage);
        result = recentProductPageFittingService.adjustListToPageInfo(result, pageIndex, perPage);

        return RecentProductsResponse.builder()
                .products(result.stream()
                        .map(conversionService::convert)
                        .collect(Collectors.toList()))
                .maximumPageNumber(maximumPageNumber)
                .build();
    }
}