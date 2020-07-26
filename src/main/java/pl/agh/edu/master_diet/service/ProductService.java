package pl.agh.edu.master_diet.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.agh.edu.master_diet.core.model.database.Product;
import pl.agh.edu.master_diet.exception.ProductNotFoundException;
import pl.agh.edu.master_diet.repository.ProductRepository;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ProductService {

    private final ProductRepository productRepository;

    public Product getProductById(final Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("No product with id: " + productId + " found"));
    }
}
