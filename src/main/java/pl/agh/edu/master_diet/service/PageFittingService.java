package pl.agh.edu.master_diet.service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PageFittingService<T> {

    public List<T> adjustListToPageSize(List<T> pageableObjects, Integer pageIndex, Integer perPage) {
        int startIndex = (pageIndex - 1) * perPage;
        int endIndex = Math.min(pageableObjects.size(), pageIndex * perPage);
        return pageableObjects.subList(startIndex, endIndex);
    }
}
