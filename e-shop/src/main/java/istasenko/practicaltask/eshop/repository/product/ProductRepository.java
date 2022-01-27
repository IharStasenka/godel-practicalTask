package istasenko.practicaltask.eshop.repository.product;

import istasenko.practicaltask.eshop.model.Product;

import java.util.Optional;
import java.util.Set;

public interface ProductRepository {
    Set<Product> getAllProducts();
    Optional<Product> findById(Long productId);
}
