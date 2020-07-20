package pl.agh.edu.master_diet.service;

import org.springframework.stereotype.Service;
import pl.agh.edu.master_diet.core.model.database.Product;
import pl.agh.edu.master_diet.core.model.rest.browser.product.ProductSearchResponse;
import pl.agh.edu.master_diet.core.model.shared.SearchResult;
import pl.agh.edu.master_diet.repository.ProductRepository;
import pl.agh.edu.master_diet.service.converter.ConversionService;

import java.util.stream.Collectors;

@Service
public class ProductSearchService extends SearchService<Product> {

    private final ConversionService conversionService;

    public ProductSearchService(ConversionService conversionService, ProductRepository browserRepository,
                                PageFittingService<Product> fittingService) {
        super(browserRepository, fittingService);
        this.conversionService = conversionService;
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
}
