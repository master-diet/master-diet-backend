package pl.agh.edu.master_diet.service;

import pl.agh.edu.master_diet.core.model.database.Browsed;
import pl.agh.edu.master_diet.core.model.shared.SearchResult;
import pl.agh.edu.master_diet.repository.BrowserRepository;

import java.util.List;

public abstract class SearchService<Searched extends Browsed> {

    private final BrowserService<Searched> browserService;
    private final PageFittingService<Searched> pageFittingService;

    public SearchService(BrowserRepository<Searched> browserRepository, PageFittingService<Searched> fittingService) {
        this.browserService = new BrowserService<>(browserRepository);
        this.pageFittingService = fittingService;
    }

    public SearchResult<Searched> searchBrowsable(String searchTerm, Integer pageIndex, Integer perPage) {
        List<Searched> searchedProducts = browserService.search(searchTerm);
        Integer maximumPageNumber = pageFittingService.calculateMaximumPageNumber(searchedProducts, perPage);
        searchedProducts = pageFittingService.adjustListToPageInfo(searchedProducts, pageIndex, perPage);
        return SearchResult.<Searched>builder()
                .result(searchedProducts)
                .maximumPage(maximumPageNumber)
                .build();
    }
}
