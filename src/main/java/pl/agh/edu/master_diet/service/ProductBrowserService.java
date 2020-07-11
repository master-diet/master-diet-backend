package pl.agh.edu.master_diet.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.agh.edu.master_diet.core.model.database.Product;
import pl.agh.edu.master_diet.core.model.database.RecentProduct;
import pl.agh.edu.master_diet.core.model.rest.product_browser.GetRecentProductsResponse;
import pl.agh.edu.master_diet.core.model.rest.product_browser.ProductSearchResponse;
import pl.agh.edu.master_diet.repository.ProductRepository;
import pl.agh.edu.master_diet.repository.RecentProductsRepository;
import pl.agh.edu.master_diet.service.converter.ConversionService;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ProductBrowserService {

    private static final int ALLOW_SINGLE_TYPO_MINIMUM_WORD_LENGTH = 5;

    private final ProductRepository productRepository;
    private final RecentProductsRepository recentProductsRepository;
    private final ConversionService conversionService;

    public ProductSearchResponse searchProducts(String searchTerm, Integer pageIndex, Integer perPage) {
        searchTerm = searchTerm.trim().toLowerCase();
        List<Product> result = productRepository.findBySearchTerm("%" + searchTerm + "%");

        if (searchTerm.length() >= ALLOW_SINGLE_TYPO_MINIMUM_WORD_LENGTH) {
            List<Product> productsForSearchTermWithTypo = searchProductsForTermWithTypo(searchTerm);
            productsForSearchTermWithTypo.removeAll(result);
            result.addAll(productsForSearchTermWithTypo);
        }

        Integer maximumPageNumber = calculateMaximumPageNumber(result, perPage);
        result = adjustListToPageInfo(result, pageIndex, perPage);

        return ProductSearchResponse.builder()
                .products(result.stream()
                        .map(conversionService::convert)
                        .collect(Collectors.toList()))
                .maximumPageNumber(maximumPageNumber)
                .build();
    }

    public GetRecentProductsResponse getRecentProducts(Integer pageIndex, Integer perPage, Long userId) {
        List<RecentProduct> result = recentProductsRepository.findByUserId(userId);
        Integer maximumPageNumber = calculateMaximumPageNumber(result, perPage);
        result = adjustListToPageInfo(result, pageIndex, perPage);

        return GetRecentProductsResponse.builder()
                .products(result.stream()
                        .map(conversionService::convert)
                        .collect(Collectors.toList()))
                .maximumPageNumber(maximumPageNumber)
                .build();
    }

    private List<Product> searchProductsForTermWithTypo(String searchTerm) {
        Set<Product> products = new LinkedHashSet<>();
        String searchTermForTypoAtTheBeginning = prepareSqlSearchPatternWithTypoAtSelectedIndex(searchTerm, 0);
        String searchTermForTypoAtTheEnd = prepareSqlSearchPatternWithTypoAtSelectedIndex(searchTerm, searchTerm.length() - 1);
        products.addAll(productRepository.findBySearchTerm(searchTermForTypoAtTheBeginning));
        products.addAll(productRepository.findBySearchTerm(searchTermForTypoAtTheEnd));

        List<Product> result = new ArrayList<>(products);
        result.sort((p1, p2) -> p2.getApprovals() - p1.getApprovals());
        return result;
    }

    private String prepareSqlSearchPatternWithTypoAtSelectedIndex(String searchTerm, int typoIndex) {
        String result;
        if (typoIndex == 0) {
            result = searchTerm.substring(1);
        } else if (typoIndex == searchTerm.length() - 1) {
            result = searchTerm.substring(0, searchTerm.length() - 1);
        } else {
            result = searchTerm.substring(0, typoIndex) + "_" + searchTerm.substring(typoIndex + 1);
        }
        return "%" + result.toLowerCase() + "%";
    }

    private Integer calculateMaximumPageNumber(List<?> pageableObjects, Integer perPage) {
        return (int) Math.ceil((double) pageableObjects.size() / perPage);
    }

    private <T> List<T> adjustListToPageInfo(List<T> pageableObjects, Integer pageIndex, Integer perPage) {
        int startIndex = (pageIndex - 1) * perPage;
        int endIndex = Math.min(pageableObjects.size(), pageIndex * perPage);
        return pageableObjects.subList(startIndex, endIndex);
    }
}
