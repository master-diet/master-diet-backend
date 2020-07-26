package pl.agh.edu.master_diet.service;

import org.springframework.stereotype.Service;
import pl.agh.edu.master_diet.core.model.database.Product;
import pl.agh.edu.master_diet.core.model.database.RecentProduct;
import pl.agh.edu.master_diet.core.model.rest.browser.product.ProductSearchResponse;
import pl.agh.edu.master_diet.core.model.rest.browser.product.RecentProductsResponse;
import pl.agh.edu.master_diet.core.model.shared.SearchResult;
import pl.agh.edu.master_diet.repository.ProductRepository;
import pl.agh.edu.master_diet.repository.RecentProductRepository;
import pl.agh.edu.master_diet.service.converter.ConversionService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProductSearchService extends SearchService<Product> {

    private final ConversionService conversionService;
    private final RecentProductRepository recentProductRepository;
    private final PageFittingService<RecentProduct> recentProductPageFittingService;

    public ProductSearchService(ConversionService conversionService,
                                ProductRepository browserRepository,
                                PageFittingService<Product> fittingService,
                                RecentProductRepository recentProductRepository,
                                PageFittingService<RecentProduct> recentProductPageFittingService) {
        super(browserRepository, fittingService);
        this.conversionService = conversionService;
        this.recentProductRepository = recentProductRepository;
        this.recentProductPageFittingService = recentProductPageFittingService;
    }

    public ProductSearchResponse searchProduct(String searchTerm, Integer pageIndex, Integer perPage) {
        SearchResult<Product> searchResult = searchBrowsable(searchTerm, pageIndex, perPage);
        return ProductSearchResponse.builder()
                .products(searchResult.getResult().stream()
                        .map(conversionService::convert)
                        .collect(Collectors.toList()))
                .totalNumberOfProducts(searchResult.getMaximumPage())
                .build();
    }

    public RecentProductsResponse getRecentProducts(Integer pageIndex, Integer perPage, Long userId) {
        List<RecentProduct> result = recentProductRepository.findByUserId(userId);
        result = removeDuplicatedRecentProducts(result);
        Integer totalNumberOfProducts = result.size();
        result = recentProductPageFittingService.adjustListToPageSize(result, pageIndex, perPage);

        return RecentProductsResponse.builder()
                .products(result.stream()
                        .map(conversionService::convert)
                        .collect(Collectors.toList()))
                .maximumPageNumber(totalNumberOfProducts)
                .build();
    }

    private List<RecentProduct> removeDuplicatedRecentProducts(List<RecentProduct> recentProducts) {
        Set<Long> productIds = new HashSet<>();
        List<RecentProduct> result = new ArrayList<>();
        for (RecentProduct recentProduct : recentProducts) {
            if (productIds.add(recentProduct.getProduct().getId())) {
                result.add(recentProduct);
            }
        }
        return result;
    }
}
