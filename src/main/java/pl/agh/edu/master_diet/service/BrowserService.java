package pl.agh.edu.master_diet.service;

import pl.agh.edu.master_diet.core.model.database.Browsed;
import pl.agh.edu.master_diet.repository.BrowserRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BrowserService<T extends Browsed> {

    private static final int ALLOW_SINGLE_TYPO_MINIMUM_WORD_LENGTH = 5;

    private final BrowserRepository<T> browserRepository;

    public BrowserService(BrowserRepository<T> browserRepository) {
        this.browserRepository = browserRepository;
    }

    public List<T> search(String searchTerm) {
        searchTerm = searchTerm.trim().toLowerCase();
        List<T> result = browserRepository.findBySearchTerm(searchTerm);

        if (searchTerm.length() >= ALLOW_SINGLE_TYPO_MINIMUM_WORD_LENGTH) {
            List<T> productsForSearchTermWithTypo = searchProductsForTermWithTypo(searchTerm);
            productsForSearchTermWithTypo.removeAll(result);
            result.addAll(productsForSearchTermWithTypo);
        }
        return result;
    }

    private List<T> searchProductsForTermWithTypo(String searchTerm) {
        Set<T> products = new HashSet<>();
        String searchTermForTypoAtTheBeginning = prepareSearchPatternWithTypoAtSelectedIndex(searchTerm, 0);
        String searchTermForTypoAtTheEnd = prepareSearchPatternWithTypoAtSelectedIndex(searchTerm,
                searchTerm.length() - 1);
        products.addAll(browserRepository.findBySearchTerm(searchTermForTypoAtTheBeginning));
        products.addAll(browserRepository.findBySearchTerm(searchTermForTypoAtTheEnd));

        List<T> result = new ArrayList<>(products);
        result.sort((p1, p2) -> p2.getApprovals() - p1.getApprovals());
        return result;
    }

    private String prepareSearchPatternWithTypoAtSelectedIndex(String searchTerm, int typoIndex) {
        String result;
        if (typoIndex == 0) {
            result = searchTerm.substring(1);
        } else if (typoIndex == searchTerm.length() - 1) {
            result = searchTerm.substring(0, searchTerm.length() - 1);
        } else {
            result = searchTerm.substring(0, typoIndex) + "_" + searchTerm.substring(typoIndex + 1);
        }
        return result.toLowerCase();
    }
}

