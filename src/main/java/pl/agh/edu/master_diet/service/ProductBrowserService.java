package pl.agh.edu.master_diet.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.agh.edu.master_diet.core.model.database.Product;
import pl.agh.edu.master_diet.core.model.rest.product_browser.ProductSearchResponse;
import pl.agh.edu.master_diet.repository.ProductRepository;
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
    private final ConversionService conversionService;

    public ProductSearchResponse searchProducts(String searchTerm) {
        searchTerm = searchTerm.trim().toLowerCase();
        List<Product> result = productRepository.findBySearchTerm(searchTerm);

        if (searchTerm.length() >= ALLOW_SINGLE_TYPO_MINIMUM_WORD_LENGTH) {
            List<Product> productsForSearchTermWithTypo = searchProductsForTermWithTypo(searchTerm);
            productsForSearchTermWithTypo.removeAll(result);
            result.addAll(productsForSearchTermWithTypo);
        }

        return ProductSearchResponse.builder()
                .products(result.stream()
                        .map(conversionService::convert)
                        .collect(Collectors.toList()))
                .build();
    }

    private List<Product> searchProductsForTermWithTypo(String searchTerm) {
        Set<Product> products = new LinkedHashSet<>();
        for (int typoIndex = 0; typoIndex < searchTerm.length(); typoIndex++) {
            String searchTermForTypo = prepareSearchTermForTypoAtIndex(searchTerm, typoIndex);
            products.addAll(productRepository.findBySearchTerm(searchTermForTypo));
        }

        List<Product> result = new ArrayList<>(products);
        result.sort((p1, p2) -> p2.getApprovals() - p1.getApprovals());
        return result;
    }

    private String prepareSearchTermForTypoAtIndex(String searchTerm, int typoIndex) {
        if (typoIndex == 0)
            return searchTerm.substring(1);
        if (typoIndex == searchTerm.length() - 1)
            return searchTerm.substring(0, searchTerm.length() - 1);

        return searchTerm.substring(0, typoIndex) + "_" + searchTerm.substring(typoIndex + 1);
    }
}
